package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class BungeeAction implements Action {

    @Override
    public @NotNull String getId() {
        return "BUNGEE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final ByteArrayDataOutput output = ByteStreams.newDataOutput();

        Arrays.asList("Connect", context.getData()).forEach(output::writeUTF);
        context.getPlayer().sendPluginMessage(context.getPlugin(), "BungeeCord", output.toByteArray());
    }
}
