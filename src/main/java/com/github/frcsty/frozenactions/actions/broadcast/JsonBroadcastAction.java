package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class JsonBroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "JSONBROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + context.getData())
        );
    }
}
