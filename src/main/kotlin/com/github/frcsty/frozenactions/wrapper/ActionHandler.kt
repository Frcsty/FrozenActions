package com.github.frcsty.frozenactions.wrapper

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.actions.broadcast.*
import com.github.frcsty.frozenactions.actions.player.*
import com.github.frcsty.frozenactions.time.parseTime
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.*
import java.util.concurrent.TimeUnit

private val ACTION_PATTERN = Regex("(.*) ?\\[(?<action>[A-Z]+?)] ?(?<arguments>.+)", RegexOption.IGNORE_CASE)
private val DELAY_PATTERN = Regex("\\[DELAY=(?<delay>\\d+[a-z])]", RegexOption.IGNORE_CASE)
private val CHANCE_PATTERN = Regex("\\[CHANCE=(?<chance>\\d+)]", RegexOption.IGNORE_CASE)
private val RANDOM = SplittableRandom()

class ActionHandler(private val plugin: Plugin) {

    val actions = mutableMapOf<String, Action>()

    fun loadDefault() {
        setOf(
                ActionbarBroadcastAction,
                ActionbarMessageAction,
                BroadcastAction,
                BungeeAction,
                CenterBroadcastAction,
                CenterMessageAction,
                ConsoleCommandAction,
                EquipItemAction,
                JsonBroadcastAction,
                JsonMessageAction,
                MessageAction,
                PlayerCommandAction,
                SoundAction,
                SoundBroadcastAction,
                TeleportAction,
                TitleBroadcastAction,
                TitleMessageAction
        ).forEach { this.actions[it.id] = it }
    }

    fun setAction(identifier: String, action: Action) {
        this.actions[identifier] = action
    }

    fun removeAction(identifier: String) {
        this.actions.remove(identifier)
    }

    fun execute(player: Player, input: List<String>) {
        input.forEach { execute(player, it) }
    }

    private fun execute(player: Player, input: String) {
        val actionHolder = getDelayAction(hasChanceAction(input) ?: return)
        val inputAction = actionHolder.action
        val match = ACTION_PATTERN.matchEntire(inputAction)

        if (match == null) {
            println("Action does not match regex $inputAction")
            return
        }

        val arguments = match.groups["arguments"]?.value ?: return
        val delay = actionHolder.delay

        val actionName = match.groups["action"]?.value?.toUpperCase()
        val action = actions[actionName] ?: return

        Bukkit.getScheduler().runTaskLater(
                plugin,
                Runnable {
                    action.run(player, arguments)
                    action.run(plugin, player, arguments)
                },
                delay
        )
    }

    private fun hasChanceAction(input: String): String? {
        val match = CHANCE_PATTERN.matchEntire(input) ?: return input
        val chanceGroup = match.groups["chance"] ?: return null
        val chance = chanceGroup.value.toInt()
        val randomValue = RANDOM.nextInt(100) + 1

        return if (randomValue <= chance) input.replace(chanceGroup.value, "") else null
    }

    private fun getDelayAction(input: String): ActionHolder {
        val match = DELAY_PATTERN.matchEntire(input) ?: return ActionHolder(input, 0L)

        val delayGroup = match.groups["delay"] ?: return ActionHolder(input, 0L)
        val delay = delayGroup.value

        return try {
            val time = delay.parseTime()
            ActionHolder(
                    action = input.replace(delayGroup.value, ""),
                    delay = time.to(TimeUnit.SECONDS) * 20L
            )
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
            ActionHolder(input, 0L)
        }
    }

    init {
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord")
    }
}