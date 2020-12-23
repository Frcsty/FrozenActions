package com.github.frcsty.frozenactions.actions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface Action {

    String getId();

    default void run(final Player player, final String data) { }
    default void run(final Plugin plugin, final Player player, final String data) {}

}
