package com.huskydreaming.medievalcharactercards.handlers.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.CharacterType;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigHandlerImpl implements ConfigHandler {

    private String language;
    private List<String> view;

    private Map<CharacterType, Integer> minValues;
    private Map<CharacterType, Integer> maxValues;

    @Override
    public void initialize(HuskyPlugin plugin) {
        plugin.saveDefaultConfig();
        FileConfiguration configuration = plugin.getConfig();

        language = configuration.getString("language");
        view = configuration.getStringList("view-format");

        minValues = limitFromSection(configuration, "min");
        maxValues = limitFromSection(configuration, "max");
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public Integer getMinValue(CharacterType characterType) {
        return minValues.get(characterType);
    }

    @Override
    public Integer getMaxValue(CharacterType characterType) {
        return maxValues.get(characterType);
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
            line = line.replace("<height>", String.valueOf(character.getHeight()));

            if (placeholderAPI) {
                line = PlaceholderAPI.setPlaceholders(offlinePlayer, line);
            }

            formattedView.add(line);
        }

        return formattedView;
    }

    private Map<CharacterType, Integer> limitFromSection(FileConfiguration configuration, String key) {
        Map<CharacterType, Integer> limits = new HashMap<>();

        limits.put(CharacterType.AGE, configuration.getInt(key + ".age"));
        limits.put(CharacterType.HEIGHT, configuration.getInt(key + ".height"));
        limits.put(CharacterType.FIRST, configuration.getInt(key + ".first-name"));
        limits.put(CharacterType.MIDDLE, configuration.getInt(key + ".middle-name"));
        limits.put(CharacterType.LAST, configuration.getInt(key + ".last-name"));
        limits.put(CharacterType.DESC, configuration.getInt(key + ".description"));

        return limits;
    }
}