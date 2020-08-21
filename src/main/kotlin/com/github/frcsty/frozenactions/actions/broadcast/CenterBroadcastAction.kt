package com.github.frcsty.frozenactions.actions.broadcast

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.actions.player.CenterMessageAction
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object CenterBroadcastAction : Action {

    override val id = "CENTERBROADCAST"

    override fun run(player: Player, data: String) {
        Bukkit.getServer().onlinePlayers.forEach { CenterMessageAction.run(it, data) }
    }
}