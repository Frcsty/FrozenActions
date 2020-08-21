package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.sendActionBarMessage
import org.bukkit.entity.Player

object ActionbarMessageAction : Action {
    override val id = "ACTIONBARMESSAGE"

    override fun run(player: Player, data: String) {
        player.sendActionBarMessage(data)
    }
}