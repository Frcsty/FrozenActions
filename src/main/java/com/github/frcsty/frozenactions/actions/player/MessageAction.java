package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.jetbrains.annotations.NotNull;

public final class MessageAction implements Action {

    @Override
    public @NotNull String getId() {
        return "MESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        Extensions.sendTranslatedMessage(context.getPlayer(), context.getData());
    }
}
