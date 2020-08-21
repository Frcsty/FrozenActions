package com.github.frcsty.frozenactions.actions.broadcast

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.sendTranslatedMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object BroadcastAction : Action {
    override val id = "BROADCAST"

    override fun run(player: Player, data: String) = Bukkit.getServer().onlinePlayers.forEach { it.sendTranslatedMessage(data) }
}