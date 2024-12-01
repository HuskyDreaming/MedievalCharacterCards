package com.huskydreaming.medievalcharactercards.commands;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.commands.abstraction.AbstractCommand;
import com.huskydreaming.huskycore.commands.annotations.CommandAnnotation;
import com.huskydreaming.huskycore.utilities.general.Parseable;
import com.huskydreaming.medievalcharactercards.enumerations.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@CommandAnnotation(label = CommandLabel.BASE)
public class BaseCommand extends AbstractCommand {

    public BaseCommand(HuskyPlugin huskyPlugin) {
        super(huskyPlugin);
    }

    @Override
    public void onCommand(CommandSender commandSender, String[] strings) {
        commandSender.sendMessage(getUsage().prefix());
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(strings.length == 1) {
            return List.of("age", "desc", "first", "gender", "last", "middle", "title", "view");
        }
        return super.onTabComplete(sender, command, s, strings);
    }

    @Override
    public Parseable getUsage() {
        return Message.GENERAL_USAGE;
    }

    @Override
    public Parseable getPermission() {
        return Message.GENERAL_USAGE;
    }
}
