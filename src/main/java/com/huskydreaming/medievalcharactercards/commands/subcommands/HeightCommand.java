package com.huskydreaming.medievalcharactercards.commands.subcommands;


import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.huskycore.utilities.NumberUtil;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.CharacterType;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;

@CommandAnnotation(label = CommandLabel.HEIGHT)
public class HeightCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;

    public HeightCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        int height;
        String number = strings[1];
        if (NumberUtil.isNumeric(number)) {
            height = Integer.parseInt(number);
        } else {
            player.sendMessage(Message.GENERAL_VALID_NUMBER.prefix());
            return;
        }

        CharacterType type = CharacterType.HEIGHT;

        int minHeight = configHandler.getMinValue(type);
        if (height < minHeight) {
            player.sendMessage(Message.GENERAL_VALID_MIN.prefix(type.getName(), minHeight));
            return;
        }

        int maxHeight = configHandler.getMaxValue(type);
        if (height > maxHeight) {
            player.sendMessage(Message.GENERAL_VALID_MAX.prefix(type.getName(), maxHeight));
            return;
        }

        try {
            Character character = characterRepository.getCharacter(player);
            character.setHeight(height);

            player.sendMessage(Message.GENERAL_SET_HEIGHT.prefix(height));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}