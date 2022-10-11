package com.github.frcsty.frozenactions.wrapper;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.actions.player.AdventureSoundAction;
import com.github.frcsty.frozenactions.actions.broadcast.*;
import com.github.frcsty.frozenactions.actions.player.*;
import com.github.frcsty.frozenactions.time.TimeAPI;
import me.mattstudios.msg.adventure.AdventureMessage;
import me.mattstudios.msg.base.MessageOptions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ActionHandler {

    private static final Pattern ACTION_PATTERN = Pattern.compile("(.*) ?\\[(?<action>[A-Z]+?)] ?(?<arguments>.+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DELAY_PATTERN = Pattern.compile("\\[DELAY=(?<delay>\\d+[a-z])]", Pattern.CASE_INSENSITIVE);
    private static final Pattern CHANCE_PATTERN = Pattern.compile("\\[CHANCE=(?<chance>\\d+)]", Pattern.CASE_INSENSITIVE);
    private static final SplittableRandom RANDOM = new SplittableRandom();

    private static AdventureMessage adventureMessage = AdventureMessage.create();

    private final Map<String, Action> actions = new HashMap<>();
    private final Plugin plugin;
    private final Logger logger;

    public ActionHandler(final Plugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();

        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public static AdventureMessage getAdventureMessage() {
        return adventureMessage;
    }

    public void loadDefaults(final boolean defaultAdventureMessage) {
        Arrays.asList(
                // Broadcast
                new AdventureSoundBroadcastAction(),
                new MiniMessageBroadcastAction(),
                new ActionbarBroadcastAction(),
                new BroadcastAction(),
                new CenterBroadcastAction(),
                new JsonBroadcastAction(),
                new SoundBroadcastAction(),
                new TitleBroadcastAction(),

                // Player
                new AdventureSoundAction(),
                new MiniMessageAction(),
                new ActionbarMessageAction(),
                new BungeeAction(),
                new CenterMessageAction(),
                new ConsoleCommandAction(),
                new JsonMessageAction(),
                new MessageAction(),
                new PlayerCommandAction(),
                new ChatAction(),
                new SoundAction(),
                new TeleportAction(),
                new TitleMessageAction(),
                new GameModeAction(),
                new PotionEffectAction(),
                new TimeAction(),
                new ParticleAction(),
                new HealthAction(),
                new FoodLevelAction()
        ).forEach(it -> actions.put(it.getId().toUpperCase(), it));

        if (defaultAdventureMessage)
            adventureMessage = AdventureMessage.create();
    }

    public void createAdventureMessage(final @NotNull MessageOptions options) {
        adventureMessage = AdventureMessage.create(options);
    }

    public void setAction(final @NotNull Action action) {
        this.actions.put(action.getId().toUpperCase(), action);
    }

    public void removeAction(final @NotNull String identifier) {
        this.actions.remove(identifier.toUpperCase());
    }

    public void execute(final @NotNull Player player, final @NotNull List<String> actions) {
        actions.forEach(it -> execute(player, it));
    }

    private void execute(final @NotNull Player player, final @NotNull String input) {
        if (hasChanceAction(input) == null) return;
        final String chanceAction = hasChanceAction(input);

        if (chanceAction == null) return;
        final ActionHolder actionHolder = getDelayAction(chanceAction);
        final String inputAction = actionHolder.getAction();
        final Matcher match = ACTION_PATTERN.matcher(inputAction);

        if (!match.matches()) {
            logger.info("Action does not match regex '" + inputAction + "'!");
            return;
        }

        final String arguments = match.group("arguments");
        final String actionName = match.group("action").toUpperCase();
        final long delay = actionHolder.getDelay();

        final Action action = this.actions.get(actionName);

        if (action == null) return;

        Bukkit.getScheduler().runTaskLater(
                plugin,
                () -> action.run(new ActionContext(plugin, player, arguments)),
                delay
        );
    }

    private @Nullable String hasChanceAction(@NotNull String input) {
        final Matcher match = CHANCE_PATTERN.matcher(input);

        if (!match.matches())
            return input;

        final String chanceGroup = match.group("chance");

        if (chanceGroup == null)
            return null;

        final int chance = Integer.parseInt(chanceGroup);
        final int randomValue = RANDOM.nextInt(100) + 1;

        return randomValue <= chance ? input.replace(chanceGroup, "") : null;
    }

    private @NotNull ActionHolder getDelayAction(final @NotNull String input) {
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
            logger.log(Level.SEVERE, "", ex);
        }

        return new ActionHolder(input, 0L);
    }

}
