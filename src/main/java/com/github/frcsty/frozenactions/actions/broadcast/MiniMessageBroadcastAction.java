package com.github.frcsty.frozenactions.actions.broadcast;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Extensions;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class MiniMessageBroadcastAction implements Action {

    @Override
    public @NotNull String getId() {
        return "MINI-MESSAGEBROADCAST";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final Component message = Extensions.getMiniTranslatedMessage(context.getData(), context.getPlayer());
        Bukkit.getOnlinePlayers().forEach(player ->
                player.sendMessage(message)
        );
    }
}

