package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object JsonMessageAction : Action {

    override val id = "JSONMESSAGE"

    override fun run(player: Player, data: String) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw ${player.name} $data")
    }
}