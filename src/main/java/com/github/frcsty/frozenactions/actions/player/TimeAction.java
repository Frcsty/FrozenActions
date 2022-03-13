package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public class TimeAction implements Action {

    @Override
    public @NotNull String getId() {
        return "time";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final World world = context.getPlayer().getWorld();

        world.setTime(context.getDataAsInt());
    }
}
