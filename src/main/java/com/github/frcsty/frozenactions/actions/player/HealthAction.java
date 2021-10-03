package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.entity.Player;

public final class HealthAction implements Action {

    @Override
    public String getId() {
        return "HEALTH";
    }

    @Override
    public void run(final ActionContext context) {
        final String health = context.getData();
        final boolean incremental = health.charAt(0) == '-' || health.charAt(0) == '+';
        final Player player = context.getPlayer();
        final double base;

        if (incremental) {
            base = player.getHealth();
        } else {
            base = 0D;
        }

        player.setHealth(Math.max(base + Integer.parseInt(health), 0D));
    }
}
