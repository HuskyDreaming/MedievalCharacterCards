package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

@CommandAnnotation(label = CommandLabel.DESC)
public class DescriptionCommand implements PlayerCommandProvider {

    private final CharacterRepository characterRepository;

    public DescriptionCommand(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if(strings.length > 1) {
            try {
                String[] formattedStrings = Arrays.copyOfRange(strings, 1, strings.length);
                String description = String.join(" ", formattedStrings);

                Character character = characterRepository.getCharacter(player);
                character.setDescription(description);

                player.sendMessage(Message.GENERAL_SET_DESCRIPTION.prefix(description));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
