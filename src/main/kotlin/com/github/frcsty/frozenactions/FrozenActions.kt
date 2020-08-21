package com.github.frcsty.frozenactions

import com.github.frcsty.frozenactions.wrapper.ActionHandler
import org.bukkit.plugin.java.JavaPlugin

class FrozenActions : JavaPlugin() {

    private val handler = ActionHandler()

    override fun onEnable() {
        handler.loadDefault()
    }

}