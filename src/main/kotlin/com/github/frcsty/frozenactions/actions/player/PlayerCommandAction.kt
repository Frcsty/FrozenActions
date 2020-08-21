package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.getTranslatedMessage
import org.bukkit.entity.Player

object PlayerCommandAction : Action {

    override val id = "PLAYERCOMMAND"

    override fun run(player: Player, data: String) {
        player.performCommand(data.getTranslatedMessage(player))
    }
}