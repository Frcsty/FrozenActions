package com.github.frcsty.frozenactions.actions.broadcast

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.sendActionBarMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object ActionbarBroadcastAction : Action {
    override val id = "ACTIONBARBROADCAST"

    override fun run(player: Player, data: String) {
        Bukkit.getServer().onlinePlayers.forEach { it.sendActionBarMessage(data) }
    }
}