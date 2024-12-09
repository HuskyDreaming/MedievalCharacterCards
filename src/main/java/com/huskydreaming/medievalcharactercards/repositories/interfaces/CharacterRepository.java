package com.huskydreaming.medievalcharactercards.repositories.interfaces;

import com.huskydreaming.huskycore.repositories.Repository;
import com.huskydreaming.medievalcharactercards.data.Character;
import org.bukkit.OfflinePlayer;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public interface CharacterRepository extends Repository {

    Character getCharacter(UUID uuid) throws ExecutionException;


    Character getCharacter(OfflinePlayer offlinePlayer) throws ExecutionException;

    void save(OfflinePlayer offlinePlayer);

    boolean delete(OfflinePlayer offlinePlayer);
}
