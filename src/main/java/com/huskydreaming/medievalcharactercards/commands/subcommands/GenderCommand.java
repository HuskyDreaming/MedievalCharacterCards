package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.GenderRepository;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CommandAnnotation(label =  CommandLabel.GENDER)
public class GenderCommand implements PlayerCommandProvider {

    private final CharacterRepository characterRepository;
    private final GenderRepository genderRepository;

    public GenderCommand(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
        genderRepository = plugin.provide(GenderRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        try {
            String gender = strings[1];
            List<String> genders = genderRepository.getGenders();

            if (!genders.contains(gender)) {
                player.sendMessage(Message.GENERAL_VALID_GENDER.prefix());
                return;
            }

            Character character = characterRepository.getCharacter(player);
            character.setGender(gender);

            player.sendMessage(Message.GENERAL_SET_GENDER.prefix(gender));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] strings) {
        if(strings.length == 2) {
            return genderRepository.getGenders();
        }
        return List.of();
    }
}