package com.damian.rpgsystem.extensions.player;

import com.damian.rpgsystem.FileManager;
import com.damian.rpgsystem.Log;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RPGPlayer {
    //Current players list
    private static List<RPGPlayer> players = new ArrayList<>();

    private Player bukkitPlayer;
    private PlayerStats stats;
    private File dataFile;
    private String className;

    //Create RPGPlayer instance for connected player, called only on join.
    private RPGPlayer(Player player) {
        this.bukkitPlayer = player;
        //Getting information from player data file
        dataFile = FileManager.getFile("PlayerData/" + player.getName() + ".rpg");
        //If player doesn't have data we have to create one for him
        if (dataFile == null) {
            dataFile = FileManager.createDataFile("PlayerData/" + player.getName() + ".rpg");
        }
        //Loading data from player file
        loadData(dataFile);
        //Adding player to current players list
        players.add(this);
        Log.info("RPGPlayer", "Adding new player: " + bukkitPlayer.getName());
    }

    //region Static methods
    public static RPGPlayer create(Player player) {
        RPGPlayer rpgPlayer = get(player);
        if (rpgPlayer == null) {
            return new RPGPlayer(player);
        }
        return rpgPlayer;
    }

    public static RPGPlayer get(Player player) {
        for (RPGPlayer pl : players) {
            if (pl.getBukkitPlayer().getUniqueId().equals(player.getUniqueId())) return pl;
        }
        return null;
    }
    //endregion

    public static List<RPGPlayer> getPlayers() {
        return new ArrayList<>(players);
    }

    public static void remove(RPGPlayer rpgPlayer) {
        players.remove(rpgPlayer);
        Log.info("RPGPlayer", "Removing player: " + rpgPlayer.getBukkitPlayer().getName());
    }

    //region PlayerData
    public void saveData() {
        Log.info("PlayerData", "Saving data...");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
        data.set("className", className);
        ConfigurationSection statsSection = data.createSection("Stats");
        statsSection.set("hp", stats.getHp());
        statsSection.set("armor", stats.getArmor());
        statsSection.set("dodgeChance", stats.getDodgeChance());
        statsSection.set("damage", stats.getDamage());
        statsSection.set("attackSpeed", stats.getAttackSpeed());
        statsSection.set("speed", stats.getSpeed());
        ConfigurationSection modifierSection = statsSection.createSection("Modifiers");
        modifierSection.set("hp", stats.getModifiers().getHpModifier());
        modifierSection.set("armor", stats.getModifiers().getArmorModifier());
        modifierSection.set("damage", stats.getModifiers().getDamageModifier());
        modifierSection.set("attackSpeed", stats.getModifiers().getAttackSpeedModifier());
        modifierSection.set("speed", stats.getModifiers().getSpeedModifier());
        try {
            data.save(dataFile);
            Log.info("PlayerData", "Data saved!");
        } catch (IOException e) {
            Log.throwError("RPGPlayer", "saveData", e);
        }
    }

    private void loadData(File dataFile) {
        YamlConfiguration data = YamlConfiguration.loadConfiguration(dataFile);
        ConfigurationSection statsSection = data.getConfigurationSection("Stats");
        if (statsSection == null) {
            stats = PlayerClasses.DEFAULT.getStats();
            className = "Default";
            Log.info("PlayerData", "New Player detected! Creating default data.");
            return;
        }
        className = data.getString("className");
        double hp = statsSection.getDouble("hp");
        double armor = statsSection.getDouble("armor");
        double dodgeChance = statsSection.getDouble("dodgeChance");
        double damage = statsSection.getDouble("damage");
        double attackSpeed = statsSection.getDouble("attackSpeed");
        double speed = statsSection.getDouble("speed");
        ConfigurationSection modifierSection = statsSection.getConfigurationSection("Modifiers");
        double hpModifier = modifierSection.getDouble("hp");
        double armorModifier = modifierSection.getDouble("armor");
        double damageModifier = modifierSection.getDouble("damage");
        double attackSpeedModifier = modifierSection.getDouble("attackSpeed");
        double speedModifier = modifierSection.getDouble("speed");
        StatModifier modifier = new StatModifier();
        modifier.setHpModifier(hpModifier);
        modifier.setArmorModifier(armorModifier);
        modifier.setDamageModifier(damageModifier);
        modifier.setAttackSpeedModifier(attackSpeedModifier);
        modifier.setSpeedModifier(speedModifier);
        stats = new PlayerStats(hp, armor, dodgeChance, damage, attackSpeed, speed, modifier);
        Log.info("PlayerData", "Data loaded from file!");
    }
    //endregion

    public Player getBukkitPlayer() {
        return bukkitPlayer;
    }

    //Giving player stats from his data
    public void applyStats() {
        bukkitPlayer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(stats.getHp());
        bukkitPlayer.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(stats.getArmor());
        bukkitPlayer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(stats.getDamage());
        bukkitPlayer.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(stats.getAttackSpeed());
        bukkitPlayer.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(stats.getSpeed());
    }

    public PlayerStats getStats() {
        return stats;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public String getClassName() {
        return className;
    }

    public void setClass(String className) {
        this.className = className;
    }
}
