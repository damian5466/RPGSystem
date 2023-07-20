package com.damian.rpgsystem.extensions.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum PlayerClasses {
    MC_DEFAULT(20, 0, 0, 1, 4, 0.1, Modifiers.DEFAULT.getModifier(), null),
    DEFAULT(10, 0, 0, 1, 3, 0.1, Modifiers.DEFAULT.getModifier(), null),
    WARRIOR(22, 2, 0.05, 2, 4, 0.1, Modifiers.DEFAULT.getModifier(), Material.IRON_AXE),
    PALADIN(24, 3, 0.05, 1, 4, 0.1, Modifiers.DEFAULT.getModifier(), Material.IRON_SWORD),
    TANK(26, 4, 0.02, 0.5, 3.75, 0.09, Modifiers.DEFAULT.getModifier(), Material.SHIELD),
    ASSASSIN(16, 0, 0.1, 4, 4.5, 0.12, Modifiers.DEFAULT.getModifier(), Material.GOLDEN_SWORD),
    ARCHER(20, 1, 0.1, 3, 3.5, 0.125, Modifiers.DEFAULT.getModifier(), Material.BOW);

    private PlayerStats stats;
    private Material guiMaterial;

    PlayerClasses(double hp, double armor, double dodgeChance, double damage, double attackSpeed, double speed, StatModifier modifiers, Material guiMaterial) {
        stats = new PlayerStats(hp, armor, dodgeChance, damage, attackSpeed, speed, modifiers);
        this.guiMaterial = guiMaterial;
    }

    public static void showClassSelector(Player p) {
        Inventory gui = Bukkit.createInventory(null, 9, Component.text("Select class").color(NamedTextColor.DARK_RED));
        for(PlayerClasses playerClass: PlayerClasses.values()) {
            String l1 = playerClass.name().substring(0, 1);
            String l2 = playerClass.name().substring(1).toLowerCase();
            String name = l1 + l2;
            if(playerClass.guiMaterial == null) continue;
            ItemStack guiItem = new ItemStack(playerClass.guiMaterial);
            ItemMeta meta = guiItem.getItemMeta();
            meta.displayName(Component.text(name).color(NamedTextColor.GOLD));
            guiItem.setItemMeta(meta);
            gui.addItem(guiItem);
        }
        p.openInventory(gui);
    }

    public PlayerStats getStats() {
        return stats;
    }
}
