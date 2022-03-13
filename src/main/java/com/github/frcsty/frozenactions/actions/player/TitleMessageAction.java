package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public final class TitleMessageAction implements Action {

    private static final int DEFAULT_FADE_IN = 5;
    private static final int DEFAULT_STAY = 10;
    private static final int DEFAULT_FADE_OUT = 5;

    @Override
    public @NotNull String getId() {
        return "TITLEMESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        final String[] args = context.getDataAsStringArray(";");

        execute(context.getPlayer(), args);
    }

    public static void execute(final Player player, final String[] args) {
        player.sendTitle(
                args[0],
                args[1],
                args.length > 3 ? DEFAULT_FADE_IN : Integer.parseInt(args[2]),
                args.length > 4 ? DEFAULT_STAY : Integer.parseInt(args[3]),
                args.length == 5 ? DEFAULT_FADE_OUT : Integer.parseInt(args[4])
        );
    }
}
