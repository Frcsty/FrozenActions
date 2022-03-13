package com.github.frcsty.frozenactions.util;

import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class Extensions {

    public static @NotNull String colorize(final @NotNull String text) {
        return ActionHandler.getAdventureMessage().parse(text).toString();
    }

    public static void sendTranslatedMessage(final @NotNull Player player, final @NotNull String text) {
        final String message = getTranslatedMessage(text, player);

        player.sendMessage(ActionHandler.getAdventureMessage().parse(message).toString());
    }

    public static @NotNull String getTranslatedMessage(@NotNull String text, final @NotNull Player player) {
        final boolean daddy = Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");

        if (daddy)
            text = PlaceholderAPI.setPlaceholders(player, text);

        return text;
    }

}
