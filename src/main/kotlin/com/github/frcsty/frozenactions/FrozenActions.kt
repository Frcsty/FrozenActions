package com.github.frcsty.frozenactions

import com.github.frcsty.frozenactions.wrapper.ActionHandler
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class FrozenActions : JavaPlugin(), Listener {

    private val handler = ActionHandler(this)

    override fun onEnable() {
        handler.loadDefault()

        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        handler.execute(event.player, listOf("[BROADCAST] Test Action"))
    }
}