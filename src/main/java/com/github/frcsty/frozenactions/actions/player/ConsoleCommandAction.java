package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.bukkit.Bukkit;

public final class ConsoleCommandAction implements Action {

    @Override
    public String getId() {
        return "CONSOLECOMMAND";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Extensions.getTranslatedMessage(context.getData(), context.getPlayer()));
    }
}
