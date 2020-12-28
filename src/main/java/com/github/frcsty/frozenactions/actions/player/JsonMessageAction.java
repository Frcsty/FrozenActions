package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;

public final class JsonMessageAction implements Action {

    @Override
    public String getId() {
        return "JSONMESSAGE";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + context.getPlayer().getName() + " " + context.getData());
    }
}
