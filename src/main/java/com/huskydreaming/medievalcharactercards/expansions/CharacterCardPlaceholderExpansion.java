package com.huskydreaming.medievalcharactercards.expansions;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ExecutionException;

public class CharacterCardPlaceholderExpansion extends PlaceholderExpansion {

    private final CharacterRepository characterRepository;

    public CharacterCardPlaceholderExpansion(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("age")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return String.valueOf(character.getAge());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("first_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getFirstName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("middle_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getMiddleName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("last_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getLastName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("title")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getTitle();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("gender")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getGender();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("description")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getDescription();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return super.onRequest(player, params);
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("age")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return String.valueOf(character.getAge());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("first_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getFirstName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("middle_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getMiddleName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("last_name")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getLastName();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("title")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getTitle();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("gender")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getGender();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        if (params.equalsIgnoreCase("description")) {
            try {
                Character character = characterRepository.getCharacter(player.getUniqueId());
                return character.getDescription();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        return super.onRequest(player, params);
    }

    @Override
    public @NotNull String getIdentifier() {
        return "medievalcharactercards";
    }

    @Override
    public @NotNull String getAuthor() {
        return "HuskyDreaming";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }
}