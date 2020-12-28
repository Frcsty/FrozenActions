package com.github.frcsty.frozenactions.actions;

import com.google.common.primitives.Ints;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class ActionContext {

    private final Plugin plugin;
    private final Player player;
    private final String data;

    public ActionContext(final Plugin plugin, final Player player, final String data) {
        this.plugin = plugin;
        this.player = player;
        this.data = data;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getData() {
        return this.data;
    }

    public int getDataAsInt() {
        final Integer data = Ints.tryParse(this.data);

        return data == null ? 0 : data;
    }

    public String[] getDataAsStringArray(final String splitterator) {
        return this.data.split(splitterator);
    }

}
