package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatAction implements Action {

    @Override
    public @NotNull String getId() {
        return "CHAT";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final Player player = context.getPlayer();

        player.chat(Extensions.getTranslatedMessage(context.getData(), player));
    }
}
