package com.huskydreaming.medievalcharactercards.repositories.interfaces;

import com.huskydreaming.huskycore.repositories.Repository;
import com.huskydreaming.medievalcharactercards.data.Character;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface CharacterRepository extends Repository {

    Character getCharacter(Player player) throws ExecutionException;

    Character getCharacter(UUID uuid) throws ExecutionException;

    void save(Player player);
}
