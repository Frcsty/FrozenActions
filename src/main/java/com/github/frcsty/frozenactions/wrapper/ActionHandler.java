package com.github.frcsty.frozenactions.wrapper;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.player.TeleportAction;
import com.github.frcsty.frozenactions.time.TimeAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ActionHandler {

    private static final Pattern ACTION_PATTERN = Pattern.compile("(.*) ?\\[(?<action>[A-Z]+?)] ?(?<arguments>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELAY_PATTERN = Pattern.compile("\\[DELAY=(?<delay>\\d+[a-z])]", Pattern.CASE_INSENSITIVE);
    private static final Pattern CHANCE_PATTERN = Pattern.compile("\\[CHANCE=(?<chance>\\d+)]", Pattern.CASE_INSENSITIVE);
    private static final SplittableRandom RANDOM = new SplittableRandom();

    private final Map<String, Action> actions = new HashMap<>();

    private final Plugin plugin;

    public ActionHandler(final Plugin plugin) {
        this.plugin = plugin;

        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void loadDefaults() {
        Arrays.asList(
            new TeleportAction()
        ).forEach(it -> actions.put(it.getId(), it));
    }

    public void setAction(final Action action) {
        this.actions.put(action.getId(), action);
    }

    public void setAction(final String identifier, final Action action) {
        this.actions.put(identifier, action);
    }

    public void removeAction(final String identifier) {
        this.actions.remove(identifier);
    }

    public void execute(final Player player, final List<String> actions) {
        actions.forEach(it -> execute(player, it));
    }

    private void execute(final Player player, final String input) {
        if (hasChanceAction(input) == null) return;
        final ActionHolder actionHolder = getDelayAction(hasChanceAction(input));
        final String inputAction = actionHolder.getAction();
        final Matcher match = ACTION_PATTERN.matcher(inputAction);

        if (!match.matches()) {
            System.out.println("Action does not match regex '" + inputAction + "'!");
            return;
        }

        final String arguments = match.group("arguments");
        final String actionName = match.group("action").toUpperCase();
        final long delay = actionHolder.getDelay();

        final Action action = this.actions.get(actionName);

        if (action == null) return;

        Bukkit.getScheduler().runTaskLater(
                plugin,
                () -> {
                    action.run(player, arguments);
                    action.run(plugin, player, arguments);
                },
                delay
        );
    }

    private String hasChanceAction(String input) {
        final Matcher match = CHANCE_PATTERN.matcher(input);

        if (!match.matches())
            return input;

        final String chanceGroup = match.group("chance");

        if (chanceGroup == null)
            return null;

        final int chance = Integer.valueOf(chanceGroup);
        final int randomValue = RANDOM.nextInt(100) + 1;

        return randomValue <= chance ? input.replace(chanceGroup, "") : null;
    }

    private ActionHolder getDelayAction(final String input) {
        final Matcher match = DELAY_PATTERN.matcher(input);

        if (!match.matches())
            return new ActionHolder(input, 0L);

        final String delayGroup = match.group("delay");

        if (delayGroup == null)
            return new ActionHolder(input, 0L);

        try {
            final TimeAPI.TimeResult time = new TimeAPI().parseTime(delayGroup);

            return new ActionHolder(
                    input.replace(delayGroup, ""),
                    time.to(TimeUnit.SECONDS) * 20
            );
        } catch (final IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        return new ActionHolder(input, 0L);
    }

}
