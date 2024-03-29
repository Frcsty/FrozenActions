package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class FoodLevelAction implements Action {

    @Override
    public @NotNull String getId() {
        return "FOODLEVEL";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final String foodLevel = context.getData();
        final boolean incremental = foodLevel.charAt(0) == '-' || foodLevel.charAt(0) == '+';
        final Player player = context.getPlayer();
        final int base;

        if (incremental) {
            base = player.getFoodLevel();
        } else {
            base = 0;
        }

        player.setFoodLevel(Math.max(base + Integer.parseInt(foodLevel), 0));
    }
}
