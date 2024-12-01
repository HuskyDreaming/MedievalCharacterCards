package com.huskydreaming.medievalcharactercards.enumerations;

import com.google.common.base.Functions;
import com.huskydreaming.huskycore.storage.Yaml;
import com.huskydreaming.huskycore.utilities.ChatUtil;
import com.huskydreaming.huskycore.utilities.general.Parseable;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public enum Message implements Parseable {

    // General Menu Items
    GENERAL_PREFIX("#b5e74aCharacterCard: #b5b5b5"),
    GENERAL_PERMISSION("You do not have permissions for that command"),
    GENERAL_PLAYER_FALSE("That player has never played before!"),
    GENERAL_SET_AGE("You have successfully set your age to &f<0>"),
    GENERAL_SET_DESCRIPTION("You have successfully set your description to &f<0>"),
    GENERAL_SET_FIRST("You have successfully set your first name to &f<0>"),
    GENERAL_SET_GENDER("You have successfully set your gender to &f<0>"),
    GENERAL_SET_LAST("You have successfully set your last name to &f<0>"),
    GENERAL_SET_MIDDLE("You have successfully set your middle name to &f<0>"),
    GENERAL_SET_TITLE("You have successfully set your title to &f<0>"),
    GENERAL_VALID_GENDER("You must provide a valid gender"),
    GENERAL_VALID_NUMBER("You must provide a valid number"),
    GENERAL_VALID_TITLE("You must provide a valid title"),
    GENERAL_USAGE("/character [age|first|gender|last|middle|title|view]");

    private final String def;
    private final List<String> list;
    private static FileConfiguration configuration;

    Message(String def) {
        this.def = def;
        this.list = null;
    }

    @Override
    public String prefix(Object... objects) {
        return ChatUtil.hex(GENERAL_PREFIX.parse() + parameterize(objects));
    }

    @Nullable
    public String parse() {
        return configuration.getString(toString(), def);
    }

    @Nullable
    public List<String> parseList() {
        List<?> objects = configuration.getList(toString(), list);
        if (objects == null) return null;
        return objects.stream().map(Functions.toStringFunction()).collect(Collectors.toList());
    }

    @NotNull
    public String toString() {
        String path = name().toLowerCase();
        if(name().contains("TITLE")) {
            return path.replace("_", ".");
        } else {
            int length = path.split("_")[0].length() + 1;
            String qualifier = path.substring(0, length).replace("_", ".");
            String subString = path.substring(length);
            return qualifier + subString;
        }
    }

    public static void load(Yaml yaml) {
        Message.configuration = yaml.getConfiguration();
        for (Message message : Message.values()) {
            configuration.set(message.toString(), message.parseList() != null ? message.parseList() : message.parse());
        }
        yaml.save();
    }
}