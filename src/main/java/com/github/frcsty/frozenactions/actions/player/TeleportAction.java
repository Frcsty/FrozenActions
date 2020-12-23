package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class TeleportAction implements Action {

    @Override
    public String getId() {
        return "TELEPORT";
    }

    @Override
    public void run(final Player player, final String data) {
        final String[] args = data.split(";");
        final Location location = new Location(
                Bukkit.getWorld(args[0]),
                Double.valueOf(args[1]),
                Double.valueOf(args[2]),
                Double.valueOf(args[3]),
                Float.valueOf(args[4]),
                Float.valueOf(args[5])
        );

        player.teleport(location);
    }

}
