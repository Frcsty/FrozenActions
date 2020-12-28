package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;

public final class MessageAction implements Action {

    @Override
    public String getId() {
        return "MESSAGE";
    }

    @Override
    public void run(final ActionContext context) {
        Extensions.sendTranslatedMessage(context.getPlayer(), context.getData());
    }
}
