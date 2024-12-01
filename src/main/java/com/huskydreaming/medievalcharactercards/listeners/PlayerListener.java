package com.huskydreaming.medievalcharactercards.listeners;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final CharacterRepository characterRepository;

    public PlayerListener(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        characterRepository.save(event.getPlayer());
    }
}