package com.damian.rpgsystem.events.gui;

import com.damian.rpgsystem.exceptions.PlayerNotFoundException;
import com.damian.rpgsystem.extensions.player.PlayerClasses;
import com.damian.rpgsystem.extensions.player.RPGPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiEvents implements Listener {

    @EventHandler
    public void classGuiEvent(InventoryClickEvent e) throws PlayerNotFoundException {
        String title = PlainTextComponentSerializer.plainText().serialize(e.getView().title());
        if(!title.equals("Select class")) return;
        e.setCancelled(true);
        if(e.getClickedInventory() == null) return;
        if(!e.getClickedInventory().equals(e.getView().getTopInventory())) return;
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        String itemName = PlainTextComponentSerializer.plainText().serialize(e.getCurrentItem().displayName()).replace("[", "").replace("]", "");
        String className = itemName.toUpperCase();
        RPGPlayer rpgPlayer = RPGPlayer.get((Player) e.getWhoClicked());
        if(rpgPlayer == null) {
            throw new PlayerNotFoundException((Player) e.getWhoClicked());
        }
        rpgPlayer.setClass(className);
        rpgPlayer.setStats(PlayerClasses.valueOf(className).getStats());
        rpgPlayer.applyStats();
        e.getWhoClicked().closeInventory();
        e.getWhoClicked().sendMessage(Component.text("You've chosen a class: " + itemName).color(NamedTextColor.GOLD));
    }
}
