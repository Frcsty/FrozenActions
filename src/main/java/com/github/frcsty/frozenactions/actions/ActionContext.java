package com.github.frcsty.frozenactions.actions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public record ActionContext(@NotNull Plugin plugin, @NotNull Player player, @NotNull String data) {

    public @NotNull Plugin getPlugin() {
        return this.plugin;
    }

    public @NotNull Player getPlayer() {
        return this.player;
    }

    public @NotNull String getData() {
        return this.data;
    }

    public int getDataAsInt() {
        return Integer.parseInt(this.data);
    }

    public @NotNull String[] getDataAsStringArray(final @NotNull String spliterator) {
        return this.data.split(spliterator);
    }

}
