package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.jetbrains.annotations.NotNull;

public class MiniMessageAction implements Action {

    @Override
    public @NotNull String getId() {
        return "MINI-MESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        context.getPlayer().sendMessage(Extensions.getMiniTranslatedMessage(context.getData(), context.getPlayer()));
    }
}

