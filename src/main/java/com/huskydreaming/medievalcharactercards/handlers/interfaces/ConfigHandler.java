package com.huskydreaming.medievalcharactercards.handlers.interfaces;

import com.huskydreaming.huskycore.handlers.interfaces.Handler;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.CharacterType;
import org.bukkit.OfflinePlayer;

import java.util.List;

public interface ConfigHandler extends Handler {
    String getLanguage();

    Integer getMinValue(CharacterType characterType);

    Integer getMaxValue(CharacterType characterType);

    List<String> getView(OfflinePlayer offlinePlayer, Character character, boolean placeholderAPI);
}
