package com.damian.rpgsystem.extensions.player;

import com.damian.rpgsystem.Log;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public enum PlayerClasses {
    MC_DEFAULT(20, 0, 0, 1, 4, 0.1, Modifiers.DEFAULT.getModifier()),
    DEFAULT(10, 0, 0, 1, 3, 0.1, Modifiers.DEFAULT.getModifier()),
    WARRIOR(22, 2, 0.05, 2, 4, 0.1, Modifiers.DEFAULT.getModifier()),
    PALADIN(24, 3, 0.05, 1, 4, 0.1, Modifiers.DEFAULT.getModifier()),
    TANK(26, 4, 0.02, 0.5, 3.75, 0.08, Modifiers.DEFAULT.getModifier()),
    ASSASSIN(16, 0, 0.1, 4, 4.5, 0.15, Modifiers.DEFAULT.getModifier()),
    ARCHER(20, 1, 0.1, 3, 3.5, 0.18, Modifiers.DEFAULT.getModifier());

    private PlayerStats stats;

    PlayerClasses(double hp, double armor, double dodgeChance, double damage, double attackSpeed, double speed, StatModifier modifiers) {
        stats = new PlayerStats(hp, armor, dodgeChance, damage, attackSpeed, speed, modifiers);
    }

    public static void showClassSelector(Player p) {
        Inventory gui = Bukkit.createInventory(null, 9, Component.text("Select class"));
        for(PlayerClasses playerClass: PlayerClasses.values()) {
            Log.info("PlayerClasses", playerClass.name());
        }
    }

    public PlayerStats getStats() {
        return stats;
    }
}
