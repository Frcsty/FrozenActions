package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Titles;
import org.bukkit.Bukkit;

public final class ActionbarBroadcastAction implements Action {

    @Override
    public String getId() {
        return "ACTIONBARBROADCAST";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
            Titles.sendActionBarMessage(player, context.getData())
        );
    }
}
