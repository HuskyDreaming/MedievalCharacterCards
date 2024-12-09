package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.huskycore.utilities.PlayerUtil;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@CommandAnnotation(label = CommandLabel.DELETE)
public class DeleteCommand implements PlayerCommandProvider {

    private final CharacterRepository characterRepository;

    public DeleteCommand(HuskyPlugin plugin) {
        characterRepository = plugin.provide(CharacterRepository.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length != 2) return;
        String string = strings[1];

        OfflinePlayer offlinePlayer = PlayerUtil.getOfflinePlayer(string);
        if (offlinePlayer == null || !offlinePlayer.hasPlayedBefore()) {
            player.sendMessage(Message.GENERAL_NEVER_PLAYED.prefix(string));
            return;
        }

        Message message = characterRepository.delete(offlinePlayer) ?
                Message.GENERAL_DELETE_SUCCESS :
                Message.GENERAL_DELETE_FAIL;

        player.sendMessage(message.prefix(string));
    }

    @Override
    public List<String> onTabComplete(Player player, String[] strings) {
        if (strings.length == 2) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return List.of();
    }
}