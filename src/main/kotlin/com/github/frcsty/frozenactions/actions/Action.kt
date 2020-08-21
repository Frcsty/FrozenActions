package com.github.frcsty.frozenactions.actions

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

interface Action {
    val id: String
    fun run(player: Player, data: String) {}
    fun run(plugin: Plugin, player: Player, data: String) {}
}