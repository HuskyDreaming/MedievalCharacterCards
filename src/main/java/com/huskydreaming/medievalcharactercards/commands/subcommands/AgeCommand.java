package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.huskycore.utilities.NumberUtil;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.entity.Player;

import java.util.concurrent.ExecutionException;

@CommandAnnotation(label = CommandLabel.AGE)
public class AgeCommand implements PlayerCommandProvider {

    private final CharacterRepository characterRepository;

    public AgeCommand(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        int age;
        String number = strings[1];
        if (NumberUtil.isNumeric(number)) {
            age = Integer.parseInt(number);
        } else {
            player.sendMessage(Message.GENERAL_VALID_NUMBER.prefix());
            return;
        }

        try {
            Character character = characterRepository.getCharacter(player);
            character.setAge(age);

            player.sendMessage(Message.GENERAL_SET_AGE.prefix(age));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}