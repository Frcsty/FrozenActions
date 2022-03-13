package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class JsonMessageAction implements Action {

    @Override
    public @NotNull String getId() {
        return "JSONMESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + context.getPlayer().getName() + " " + context.getData());
    }
}
