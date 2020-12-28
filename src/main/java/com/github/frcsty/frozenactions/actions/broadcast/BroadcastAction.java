package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.bukkit.Bukkit;

public final class BroadcastAction implements Action {

    @Override
    public String getId() {
        return "BROADCAST";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
                Extensions.sendTranslatedMessage(player, context.getData())
        );
    }
}
