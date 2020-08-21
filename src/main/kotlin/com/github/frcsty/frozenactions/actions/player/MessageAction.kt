package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.sendTranslatedMessage
import org.bukkit.entity.Player

object MessageAction : Action {
    override val id = "MESSAGE"

    override fun run(player: Player, data: String) = player.sendTranslatedMessage(data)
}