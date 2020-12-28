package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.Titles;
import org.bukkit.entity.Player;

public final class TitleMessageAction implements Action {

    private static final int DEFAULT_FADE_IN = 5;
    private static final int DEFAULT_STAY = 10;
    private static final int DEFAULT_FADE_OUT = 5;

    @Override
    public String getId() {
        return "TITLEMESSAGE";
    }

    @Override
    public void run(final ActionContext context) {
        final String[] args = context.getDataAsStringArray(";");

        execute(context.getPlayer(), args);
    }

    public static void execute(final Player player, final String[] args) {
        Titles.sendPlayerTitle(
                player,
                args[0],
                args[1],
                args.length > 3 ? DEFAULT_FADE_IN : Integer.valueOf(args[2]),
                args.length > 4 ? DEFAULT_STAY : Integer.valueOf(args[3]),
                args.length == 5 ? DEFAULT_FADE_OUT : Integer.valueOf(args[4])
        );
    }
}
