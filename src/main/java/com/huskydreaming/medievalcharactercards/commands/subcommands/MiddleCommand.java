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

@CommandAnnotation(label =  CommandLabel.MIDDLE)
public class MiddleCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;

    public MiddleCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        String middleName = strings[1];
        CharacterType type = CharacterType.MIDDLE;

        int minMiddleName = configHandler.getMinValue(type);
        if (middleName.length() < minMiddleName) {
            player.sendMessage(Message.GENERAL_VALID_MIN_CHAR.prefix(type.getName(), minMiddleName));
            return;
        }

        int maxMiddleName = configHandler.getMaxValue(type);
        if (middleName.length() > maxMiddleName) {
            player.sendMessage(Message.GENERAL_VALID_MAX_CHAR.prefix(type.getName(), maxMiddleName));
            return;
        }

        try {

            Character character = characterRepository.getCharacter(player);
            character.setMiddleName(middleName);

            player.sendMessage(Message.GENERAL_SET_MIDDLE.prefix(middleName));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}