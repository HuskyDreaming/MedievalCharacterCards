package com.huskydreaming.medievalcharactercards.repositories.implementations;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CharacterRepositoryImpl implements CharacterRepository {

    private HuskyPlugin plugin;

    @Override
    public void deserialize(HuskyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void serialize(HuskyPlugin plugin) {
        characters.invalidateAll();
    }

    private final RemovalListener<UUID, Character> removalListener = notification -> {
        Yaml yaml = new Yaml("characters/" + notification.getKey());
        yaml.load(plugin);
        FileConfiguration configuration = yaml.getConfiguration();

        Character character = notification.getValue();
        if (character != null) {
            configuration.set("age", character.getAge());
            configuration.set("gender", character.getGender());
            configuration.set("first-name", character.getFirstName());
            configuration.set("middle-name", character.getMiddleName());
            configuration.set("last-name", character.getLastName());
            configuration.set("title", character.getTitle());
            configuration.set("description", character.getDescription());

            yaml.save();
            plugin.getLogger().info("[Storage] Successfully saved character for " + notification.getKey());
        }
    };

    private final Cache<UUID, Character> characters = CacheBuilder
            .newBuilder()
            .removalListener(removalListener)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    @Override
    public Character getCharacter(UUID uuid) throws ExecutionException {
        return characters.get(uuid, () -> {
            Character character = new Character();
            Yaml yaml = new Yaml("characters/" + uuid);
            yaml.load(plugin);

            FileConfiguration configuration = yaml.getConfiguration();
            int age = configuration.getInt("age");
            character.setAge(age);

            String title = configuration.getString("title");
            if (title != null) character.setTitle(title);

            String firstName = configuration.getString("first-name");
            if (firstName != null) character.setFirstName(firstName);

            String middleName = configuration.getString("middle-name");
            if (middleName != null) character.setMiddleName(middleName);

            String lastName = configuration.getString("last-name");
            if (lastName != null) character.setLastName(lastName);

            String gender = configuration.getString("gender");
            if (gender != null) character.setGender(gender);

            String description = configuration.getString("description");
            if (description != null) character.setDescription(description);

            return character;
        });
    }

    @Override
    public Character getCharacter(Player player) throws ExecutionException {
        return getCharacter(player.getUniqueId());
    }

    @Override
    public void save(Player player) {
        characters.invalidate(player.getUniqueId());
    }
}