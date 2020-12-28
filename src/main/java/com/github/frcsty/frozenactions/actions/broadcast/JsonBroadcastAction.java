package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;

public final class JsonBroadcastAction implements Action {

    @Override
    public String getId() {
        return "JSONBROADCAST";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + context.getData())
        );
    }
}
