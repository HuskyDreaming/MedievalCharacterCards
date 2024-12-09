package com.huskydreaming.medievalcharactercards.repositories.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.TitleRepository;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

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
        if (tempTitles.isEmpty()) {
            titles.add("Mr");
            titles.add("Mrs");
            titles.add("Miss");
            titles.add("Master");

            configuration.set("titles", tempTitles);
            yaml.save();
        } else {
            titles.addAll(tempTitles);
        }

        titles.add("none");
    }

    @Override
    public void serialize(HuskyPlugin plugin) {
        FileConfiguration configuration = yaml.getConfiguration();
        titles.remove("none");
        configuration.set("titles", titles);
        yaml.save();
    }

    @Override
    public List<String> getTitles(Player player, boolean luckperms, boolean townyAdvanced) {
        List<String> titles = new ArrayList<>();

        for (String title : this.titles) {
            if (player.hasPermission("medievalcharactercards.title." + title) && !titles.contains(title)) {
                titles.add(title);
            }
        }
        return titles;
    }
}