package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public final class TeleportAction implements Action {

    @Override
    public String getId() {
        return "TELEPORT";
    }

    @Override
    public void run(final ActionContext context) {
        final String[] arguments = context.getDataAsStringArray(";");
        final Location location = new Location(
                Bukkit.getWorld(arguments[0]),
                Double.valueOf(arguments[1]),
                Double.valueOf(arguments[2]),
                Double.valueOf(arguments[3]),
                Float.valueOf(arguments[4]),
                Float.valueOf(arguments[5])
        );

        context.getPlayer().teleport(location);
    }

}
