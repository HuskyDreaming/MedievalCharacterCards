package com.huskydreaming.medievalcharactercards.handlers.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.expansions.CharacterCardPlaceholderExpansion;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.DependencyHandler;
import org.bukkit.plugin.PluginManager;

public class DependencyHandlerImpl implements DependencyHandler {

    private boolean placeholderApi;

    @Override
    public void postInitialize(HuskyPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        if (pluginManager.isPluginEnabled("PlaceholderAPI")) {
            new CharacterCardPlaceholderExpansion(plugin).register();
            placeholderApi = true;
            plugin.getLogger().info("[Dependency] Successfully hooked into PlaceholderAPI");
        }
    }

    @Override
    public boolean isPlaceholderAPI() {
        return placeholderApi;
    }
}
