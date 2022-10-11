package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AdventureSoundAction implements Action {

    @Override
    public @NotNull String getId() {
        return "ADVENTURE-SOUND";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final String[] args = context.getDataAsStringArray(";");

        final Key key = Key.key(args[0]);
        final Sound.Source source;
        try {
            source = Sound.Source.valueOf(args[1]);
        } catch (Exception ignored) {
            context.plugin().getSLF4JLogger().warn("Could not find sound source: " + args[1]);
            return;
        }

        final float volume;
        final float pitch;

        try {
            volume = Float.parseFloat(args[1]);
            pitch = Float.parseFloat(args[2]);
        } catch (Exception ignored) {
            context.plugin().getSLF4JLogger().warn("Volume and pitch must be floating point numbers!");
            return;
        }

        final Sound sound = Sound.sound(key, source, volume, pitch);

        final Player player = context.getPlayer();
        player.playSound(sound);
    }
}
