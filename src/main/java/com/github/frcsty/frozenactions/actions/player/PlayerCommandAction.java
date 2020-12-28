package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.bukkit.entity.Player;

public final class PlayerCommandAction implements Action {

    @Override
    public String getId() {
        return "PLAYERCOMMAND";
    }

    @Override
    public void run(final ActionContext context) {
        final Player player = context.getPlayer();

        player.performCommand(Extensions.getTranslatedMessage(context.getData(), player));
    }
}
