package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.actions.broadcast.TitleBroadcastAction
import org.bukkit.entity.Player

object TitleMessageAction : Action {

    override val id = "TITLEMESSAGE"

    override fun run(player: Player, data: String) {
        val args = data.split(";")

        TitleBroadcastAction.run(player, (listOf("PLAYER") + args.drop(1)).joinToString(";"))
    }
}