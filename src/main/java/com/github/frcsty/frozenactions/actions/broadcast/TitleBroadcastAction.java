package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.actions.player.TitleMessageAction;
import org.bukkit.Bukkit;

public final class TitleBroadcastAction implements Action {

    @Override
    public String getId() {
        return "TITLEBROADCAST";
    }

    @Override
    public void run(final ActionContext context) {
        Bukkit.getOnlinePlayers().forEach(player -> TitleMessageAction.execute(player, context.getDataAsStringArray(";")));
    }
}
