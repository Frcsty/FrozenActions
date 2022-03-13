package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ParticleAction implements Action {

    @Override
    public @NotNull String getId() {
        return "PARTICLE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final Player player = context.getPlayer();
        final String[] arguments = context.getDataAsStringArray(";");

        player.spawnParticle(
                Particle.valueOf(arguments[0]),
                player.getLocation(),
                Integer.parseInt(arguments[1]),
                Double.parseDouble(arguments[2]),
                Double.parseDouble(arguments[3]),
                Double.parseDouble(arguments[4]),
                Double.parseDouble(arguments[5])
        );
    }
}
