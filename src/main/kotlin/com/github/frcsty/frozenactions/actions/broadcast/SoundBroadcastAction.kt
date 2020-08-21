package com.github.frcsty.frozenactions.actions.broadcast

import com.github.frcsty.frozenactions.actions.Action
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player

object SoundBroadcastAction : Action {

    override val id = "SOUNDBROADCAST"

    override fun run(player: Player, data: String) {
        val args = data.split(";")
        val sound = Sound.valueOf(args[0])
        val volume: Float = Float.fromBits(args[1].toInt())
        val pitch: Float = Float.fromBits(args[2].toInt())

        Bukkit.getServer().onlinePlayers.forEach { it.playSound(it.location, sound, volume, pitch) }
    }
}