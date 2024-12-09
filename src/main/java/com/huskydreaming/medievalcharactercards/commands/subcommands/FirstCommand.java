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

@CommandAnnotation(label =  CommandLabel.FIRST)
public class FirstCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;

    public FirstCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        String firstName = strings[1];
        CharacterType type = CharacterType.FIRST;

        int minFirstName = configHandler.getMinValue(type);
        if(firstName.length() < minFirstName) {
            player.sendMessage(Message.GENERAL_VALID_MIN_CHAR.prefix(type.getName(), minFirstName));
            return;
        }

        int maxFirstName = configHandler.getMaxValue(type);
        if(firstName.length() > maxFirstName) {
            player.sendMessage(Message.GENERAL_VALID_MAX_CHAR.prefix(type.getName(), maxFirstName));
            return;
        }

        try {
            Character character = characterRepository.getCharacter(player);
            character.setFirstName(firstName);

            player.sendMessage(Message.GENERAL_SET_FIRST.prefix(firstName));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}