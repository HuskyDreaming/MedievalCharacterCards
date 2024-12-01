package com.huskydreaming.medievalcharactercards.repositories.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.TitleRepository;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class TitleRepositoryImpl implements TitleRepository {

    private final List<String> titles = new ArrayList<>();
    private Yaml yaml;

    @Override
    public void deserialize(HuskyPlugin plugin) {
        yaml = new Yaml("titles");
        yaml.load(plugin);

        FileConfiguration configuration = yaml.getConfiguration();
        List<String> tempTitles = configuration.getStringList("titles");
        if(tempTitles.isEmpty()) {
            titles.add("Mr");
            titles.add("Mrs");
            titles.add("Miss");
            titles.add("Master");

            configuration.set("titles", tempTitles);
            yaml.save();
        } else {
            titles.addAll(tempTitles);
        }
    }

    @Override
    public void serialize(HuskyPlugin plugin) {
        FileConfiguration configuration = yaml.getConfiguration();
        configuration.set("titles", titles);
        yaml.save();
    }

    @Override
    public List<String> getTitles() {
        return List.copyOf(titles);
    }
}