package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.CharacterType;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;

@CommandAnnotation(label =  CommandLabel.LAST)
public class LastCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;

    public LastCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        String lastName = strings[1];
        CharacterType type = CharacterType.LAST;

        int minLastName = configHandler.getMinValue(type);
        if (lastName.length() < minLastName) {
            player.sendMessage(Message.GENERAL_VALID_MIN_CHAR.prefix(type.getName(), minLastName));
            return;
        }

        int maxLastName = configHandler.getMaxValue(type);
        if (lastName.length() > maxLastName) {
            player.sendMessage(Message.GENERAL_VALID_MAX_CHAR.prefix(type.getName(), maxLastName));
            return;
        }

        try {
            Character character = characterRepository.getCharacter(player);
            character.setLastName(lastName);

            player.sendMessage(Message.GENERAL_SET_LAST.prefix(lastName));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}