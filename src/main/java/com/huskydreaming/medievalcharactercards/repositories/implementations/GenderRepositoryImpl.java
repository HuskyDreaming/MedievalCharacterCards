package com.huskydreaming.medievalcharactercards.repositories.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.GenderRepository;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class GenderRepositoryImpl implements GenderRepository {

    private final List<String> genders = new ArrayList<>();
    private Yaml yaml;

    @Override
    public void postDeserialize(HuskyPlugin plugin) {
        yaml = new Yaml("genders");
        yaml.load(plugin);

        FileConfiguration configuration = yaml.getConfiguration();
        List<String> tempGenders = configuration.getStringList("genders");
        if(tempGenders.isEmpty()) {
            genders.add("Male");
            genders.add("Female");
            genders.add("Other");

            configuration.set("genders", genders);
            yaml.save();
        } else {
            this.genders.addAll(tempGenders);
        }
    }

    @Override
    public void serialize(HuskyPlugin plugin) {
        FileConfiguration configuration = yaml.getConfiguration();
        configuration.set("genders", genders);
        yaml.save();
    }

    @Override
    public List<String> getGenders() {
        return List.copyOf(genders);
    }
}