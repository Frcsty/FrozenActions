package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public final class SoundAction implements Action {

    @Override
    public String getId() {
        return "SOUND";
    }

    @Override
    public void run(final ActionContext context) {
        final String[] args = context.getDataAsStringArray(";");

        final Sound sound = Sound.valueOf(args[0].toUpperCase());
        final float volume = Float.valueOf(args[1]);
        final float pitch = Float.valueOf(args[2]);

        final Player player = context.getPlayer();
        player.playSound(player.getLocation(), sound, volume, pitch);
    }
}
