package com.huskydreaming.medievalcharactercards.commands.subcommands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.commands.providers.PlayerCommandProvider;
import com.huskydreaming.huskycore.utilities.ChatUtil;
import com.huskydreaming.huskycore.utilities.PlayerUtil;
import com.huskydreaming.medievalcharactercards.commands.CommandLabel;
import com.huskydreaming.medievalcharactercards.data.Character;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.ConfigHandler;
import com.huskydreaming.medievalcharactercards.handlers.interfaces.DependencyHandler;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.CharacterRepository;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@CommandAnnotation(label = CommandLabel.VIEW)
public class ViewCommand implements PlayerCommandProvider {

    private final ConfigHandler configHandler;
    private final CharacterRepository characterRepository;
    private final DependencyHandler dependencyHandler;

    public ViewCommand(HuskyPlugin plugin) {
        configHandler = plugin.provide(ConfigHandler.class);
        characterRepository = plugin.provide(CharacterRepository.class);
        dependencyHandler = plugin.provide(DependencyHandler.class);
    }

    @Override
    public void onCommand(Player player, String[] strings) {
        if (strings.length < 2) return;

        OfflinePlayer offlinePlayer = PlayerUtil.getOfflinePlayer(strings[1]);
        if (offlinePlayer == null) {
            player.sendMessage(Message.GENERAL_PLAYER_FALSE.prefix());
            return;
        }

        try {
            Character character = characterRepository.getCharacter(offlinePlayer.getUniqueId());
            List<String> view = configHandler.getView(offlinePlayer, character, dependencyHandler.isPlaceholderAPI());

            for(String line : view) {
                player.sendMessage(ChatUtil.hex(line));
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> onTabComplete(Player player, String[] strings) {
        if(strings.length == 2) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        }
        return List.of();
    }
}