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

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@CommandAnnotation(label = CommandLabel.DESC)
public class DescriptionCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;

    public DescriptionCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length > 1) {
            String[] formattedStrings = Arrays.copyOfRange(strings, 1, strings.length);
            String description = String.join(" ", formattedStrings);

            CharacterType type = CharacterType.DESC;

            int minDescription = configHandler.getMinValue(type);
            if (description.length() < minDescription) {
                player.sendMessage(Message.GENERAL_VALID_MIN_CHAR.prefix(type.getName(), minDescription));
                return;
            }

            int maxDescription = configHandler.getMaxValue(type);
            if (description.length() > maxDescription) {
                player.sendMessage(Message.GENERAL_VALID_MAX_CHAR.prefix(type.getName(), maxDescription));
                return;
            }

            try {
                Character character = characterRepository.getCharacter(player);
                character.setDescription(description);

                player.sendMessage(Message.GENERAL_SET_DESCRIPTION.prefix(description));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}