package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public final class TeleportAction implements Action {

    @Override
    public @NotNull String getId() {
        return "TELEPORT";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final String[] arguments = context.getDataAsStringArray(";");
        final Location location = new Location(
                Bukkit.getWorld(arguments[0]),
                Double.parseDouble(arguments[1]),
                Double.parseDouble(arguments[2]),
                Double.parseDouble(arguments[3]),
                Float.parseFloat(arguments[4]),
                Float.parseFloat(arguments[5])
        );

        context.getPlayer().teleport(location);
    }

}
