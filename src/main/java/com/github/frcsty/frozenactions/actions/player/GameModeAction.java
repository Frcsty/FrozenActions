package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.GameMode;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public final class GameModeAction implements Action {
    private static final Map<String, GameMode> GAME_MODES = Arrays.stream(GameMode.values())
            .collect(Collectors.toUnmodifiableMap(gameMode -> gameMode.toString().toLowerCase(), gameMode -> gameMode));
    private static final GameMode DEFAULT_GAME_MODE = GameMode.ADVENTURE;

    @Override
    public String getId() {
        return "GAMEMODE";
    }

    @Override
    public void run(final ActionContext context) {
        context.getPlayer().setGameMode(GAME_MODES.getOrDefault(context.getData().toLowerCase(), DEFAULT_GAME_MODE));
    }
}
