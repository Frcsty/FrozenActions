package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.jetbrains.annotations.NotNull;

public final class ActionbarMessageAction implements Action {

    @Override
    public @NotNull String getId() {
        return "ACTIONBARMESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        context.getPlayer().sendActionBar(context.getData());
    }
}
