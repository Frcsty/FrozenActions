package com.github.frcsty.frozenactions.actions.player

import com.github.frcsty.frozenactions.actions.Action
import com.github.frcsty.frozenactions.util.DefaultFontInfo
import com.github.frcsty.frozenactions.util.color
import org.bukkit.entity.Player

object CenterMessageAction : Action {

    private const val CENTER_PX = 154
    private const val MAX_PX = 250
    override val id = "CENTERMESSAGE"

    override fun run(player: Player, data: String) {
        var message = data.color()
        var messagePxSize = 0

        var previousCode = false
        var isBold = false

        var charIndex = 0
        var lastSpaceIndex = 0
        var toSendAfter: String? = null
        var recentColorCode = ""

        for (c in message.toCharArray()) {
            if (c == '§') {
                previousCode = true
                continue
            } else if (previousCode) {
                previousCode = false
                recentColorCode = "§$c"
                if (c == 'l' || c == 'L') {
                    isBold = true
                    continue
                } else {
                    isBold = false
                }
            } else if (c == ' ') {
                lastSpaceIndex = charIndex
            } else {
                val dfi: DefaultFontInfo = DefaultFontInfo.getDefaultFontInfo(c)
                messagePxSize += if (isBold) dfi.boldLength else dfi.length
                messagePxSize++
            }
            if (messagePxSize >= MAX_PX) {
                toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1)
                message = message.substring(0, lastSpaceIndex + 1)
                break
            }
            charIndex++
        }

        val halvedMessageSize = messagePxSize / 2
        val toCompensate: Int = CENTER_PX - halvedMessageSize
        val spaceLength: Int = DefaultFontInfo.SPACE.length + 1
        var compensated = 0

        val sb = StringBuilder()
        while (compensated < toCompensate) {
            sb.append(" ")
            compensated += spaceLength
        }

        player.sendMessage(sb.toString() + message)
        if (toSendAfter != null) {
            run(player, toSendAfter)
        }
    }
}