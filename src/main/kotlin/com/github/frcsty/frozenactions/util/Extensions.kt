package com.github.frcsty.frozenactions.util

import me.clip.placeholderapi.PlaceholderAPI
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.regex.Pattern

private val HEX_PATTERN: Pattern = Pattern.compile("#<([A-Fa-f0-9]){6}>")

fun String.color(): String {
    var translation = this

    if (Reflection.is16) {
        var matcher = HEX_PATTERN.matcher(translation)

        while (matcher.find()) {
            var hexString = matcher.group()

            hexString = "#" + hexString.substring(2, hexString.length - 1)
            val hex: ChatColor = ChatColor.of(hexString)
            val before = translation.substring(0, matcher.start())
            val after = translation.substring(matcher.end())

            translation = before + hex + after
            matcher = HEX_PATTERN.matcher(translation)
        }
    }

    return ChatColor.translateAlternateColorCodes('&', translation)
}

fun Player.sendTranslatedMessage(msg: String) {
    val message = msg.getTranslatedMessage(this)

    if (Reflection.is16) {
        this.spigot().sendMessage(*TextComponent.fromLegacyText(message.color()))
        return
    }

    this.sendMessage(message.color())
}

fun String.getTranslatedMessage(player: Player): String {
    var message = this
    val daddy: Plugin? = Bukkit.getPluginManager().getPlugin("PlaceholderAPI")

    if (daddy != null && daddy.isEnabled) {
        message = PlaceholderAPI.setPlaceholders(player, message)
    }

    return message
}