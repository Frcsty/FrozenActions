package com.github.frcsty.frozenactions.util;

import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Extensions {

    public static String colorize(final String text) {
        return ActionHandler.getBukkitMessage().parse(text).toString();
    }

    public static void sendTranslatedMessage(final Player player, final String text) {
        final String message = getTranslatedMessage(text, player);

        player.sendMessage(ActionHandler.getBukkitMessage().parse(message).toString());
    }

    public static String getTranslatedMessage(String text, final Player player) {
        final boolean daddy = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

        if (daddy)
            text = PlaceholderAPI.setPlaceholders(player, text);

        return text;
    }

}
