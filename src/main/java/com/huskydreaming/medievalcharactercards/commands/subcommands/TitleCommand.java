package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.TitleRepository;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CommandAnnotation(label =  CommandLabel.TITLE)
public class TitleCommand implements PlayerCommandProvider {

    private final CharacterRepository characterRepository;
    private final TitleRepository titleRepository;

    public TitleCommand(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
        titleRepository = plugin.provide(TitleRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        try {
            String title = strings[1];
            List<String> titles = titleRepository.getTitles();

            if(!titles.contains(title)) {
                player.sendMessage(Message.GENERAL_VALID_TITLE.prefix());
                return;
            }

            Character character = characterRepository.getCharacter(player);
            character.setTitle(title);

            player.sendMessage(Message.GENERAL_SET_TITLE.prefix(title));
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] strings) {
        if(strings.length == 2) {
            return titleRepository.getTitles();
        }
        return List.of();
    }
}