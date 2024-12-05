package com.huskydreaming.medievalcharactercards.repositories.interfaces;

import com.huskydreaming.huskycore.repositories.Repository;
import org.bukkit.entity.Player;

import java.util.List;

public interface TitleRepository extends Repository {

    List<String> getTitles(Player player, boolean luckPerms, boolean townyAdvanced);
}
