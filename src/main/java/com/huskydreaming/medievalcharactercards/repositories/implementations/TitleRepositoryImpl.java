package com.huskydreaming.medievalcharactercards.repositories.implementations;

import com.huskydreaming.huskycore.HuskyPlugin;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.medievalcharactercards.repositories.interfaces.TitleRepository;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Resident;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class TitleRepositoryImpl implements TitleRepository {

    private final List<String> titles = new ArrayList<>();
    private Yaml yaml;

    @Override
    public void deserialize(HuskyPlugin plugin) {
        yaml = new Yaml("titles");
        yaml.load(plugin);

        FileConfiguration configuration = yaml.getConfiguration();
        List<String> tempTitles = configuration.getStringList("titles");
        if (tempTitles.isEmpty()) {
            titles.add("Mr");
            titles.add("Mrs");
            titles.add("Miss");
            titles.add("Master");

            configuration.set("titles", tempTitles);
            yaml.save();
        } else {
            titles.addAll(tempTitles);
        }

        titles.add("none");
    }

    @Override
    public void serialize(HuskyPlugin plugin) {
        FileConfiguration configuration = yaml.getConfiguration();
        titles.remove("none");
        configuration.set("titles", titles);
        yaml.save();
    }

    @Override
    public List<String> getTitles(Player player, boolean luckperms, boolean townyAdvanced) {
        List<String> titles = new ArrayList<>();

        if (townyAdvanced) {
            Resident resident = TownyAPI.getInstance().getResident(player);
            if (resident != null) titles.add(resident.getTitle());
        }

        if(luckperms) {
            UUID uuid = player.getUniqueId();
            UserManager userManager = LuckPermsProvider.get().getUserManager();
            CompletableFuture<User> userFuture = userManager.loadUser(uuid);

            userFuture.thenAcceptAsync(user -> {
                Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
                inheritedGroups.forEach(group -> group.getNodes().forEach(node -> {
                    if(node.getKey().startsWith("displayname.")) {
                        String[] strings = node.getKey().split("\\.");
                        if(strings.length == 2 && !titles.contains(strings[1])) {
                            titles.add(strings[1]);
                        }
                    }
                }));
            });
        }

        for (String title : this.titles) {
            if (player.hasPermission("medievalcharactercards.title." + title) && !titles.contains(title)) {
                titles.add(title);
            }
        }
        return titles;
    }
}