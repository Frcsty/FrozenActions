package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Titles;

public final class ActionbarMessageAction implements Action {

    @Override
    public String getId() {
        return "ACTIONBARMESSAGE";
    }

    @Override
    public void run(final ActionContext context) {
        Titles.sendActionBarMessage(context.getPlayer(), context.getData());
    }
}
