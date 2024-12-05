package com.huskydreaming.medievalcharactercards.handlers.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.expansions.CharacterCardPlaceholderExpansion;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.DependencyHandler;
import org.bukkit.plugin.PluginManager;

public class DependencyHandlerImpl implements DependencyHandler {

    private boolean luckPermsAPI;
    private boolean placeholderAPI;
    private boolean townyAdvancedAPI;

    @Override
    public void postInitialize(HuskyPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        if (pluginManager.isPluginEnabled("PlaceholderAPI")) {
            new CharacterCardPlaceholderExpansion(plugin).register();
            placeholderAPI = true;
            plugin.getLogger().info("[Dependency] Successfully hooked into PlaceholderAPI");
        }

        if (pluginManager.isPluginEnabled("TownyAdvanced")) {
            townyAdvancedAPI = true;
            plugin.getLogger().info("[Dependency] Successfully hooked into TownyAdvanced");
        }

        if (pluginManager.isPluginEnabled("LuckPerms")) {
            luckPermsAPI = true;
        }
    }

    @Override
    public boolean isLuckPermsAPI() {
        return luckPermsAPI;
    }

    @Override
    public boolean isPlaceholderAPI() {
        return placeholderAPI;
    }

    @Override
    public boolean isTownyAdvancedAPI() {
        return townyAdvancedAPI;
    }
}
