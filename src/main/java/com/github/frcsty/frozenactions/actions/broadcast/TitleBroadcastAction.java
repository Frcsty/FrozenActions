package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.actions.player.TitleMessageAction;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class TitleBroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "TITLEBROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player -> TitleMessageAction.execute(player, context.getDataAsStringArray(";")));
    }
}
