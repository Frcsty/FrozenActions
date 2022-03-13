package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class ActionbarBroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "ACTIONBARBROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
            player.sendActionBar(context.getData())
        );
    }
}
