package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class BroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "BROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
                Extensions.sendTranslatedMessage(player, context.getData())
        );
    }
}
