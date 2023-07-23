package com.damian.rpgsystem.extensions.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class RPGItem {
    private static List<RPGItem> savedItems;

    private String codeName;
    private Material material;
    private Component name;
    private List<Component> lore;

    public RPGItem(String codeName, Material material, Component name, List<Component> lore) {
        this.codeName = codeName;
        this.material = material;
        this.name = name;
        this.lore = lore;
    }

    public RPGItem(String codeName, Material material, Component name) {
        this.codeName = codeName;
        this.material = material;
        this.name = name;
        this.lore = new ArrayList<>();
    }

    public RPGItem(String codeName, Material material, String name) {
        this.codeName = codeName;
        this.material = material;
        this.name = Component.text(name);
        this.lore = new ArrayList<>();
    }

    public ItemStack get() {
        ItemStack item = new ItemStack(this.material);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(this.name);
        meta.lore(lore);
        meta.getPersistentDataContainer().set(NamespacedKey.fromString("rpgitem"), PersistentDataType.STRING, codeName);
        item.setItemMeta(meta);
        return item;
    }
}
