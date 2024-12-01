package com.huskydreaming.medievalcharactercards.handlers.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandlerImpl implements ConfigHandler {

    private String language;
    private List<String> view;

    @Override
    public void initialize(HuskyPlugin plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration configuration = plugin.getConfig();

        language = configuration.getString("language");
        view = configuration.getStringList("view-format");
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public List<String> getView(OfflinePlayer offlinePlayer, Character character, boolean placeholderAPI) {
        List<String> formattedView = new ArrayList<>();
        for (String line : view) {
            if (offlinePlayer != null) {
                String name = offlinePlayer.getName();
                if (name != null) line = line.replace("<player-name>", name);
            }

            line = line.replace("<first>", character.getFirstName());
            line = line.replace("<middle>", character.getMiddleName());
            line = line.replace("<last>", character.getLastName());
            line = line.replace("<title>", character.getTitle());
            line = line.replace("<gender>", character.getGender());
            line = line.replace("<description>", character.getDescription());
            line = line.replace("<age>", String.valueOf(character.getAge()));

            if (placeholderAPI) {
                line = PlaceholderAPI.setPlaceholders(offlinePlayer, line);
            }

            formattedView.add(line);
        }

        return formattedView;
    }
}