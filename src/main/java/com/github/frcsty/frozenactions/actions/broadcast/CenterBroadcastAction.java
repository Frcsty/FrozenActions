package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.actions.player.CenterMessageAction;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class CenterBroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "CENTERBROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player ->
                CenterMessageAction.execute(new ActionContext(context.getPlugin(), player, context.getData()))
        );
    }
}
