package com.github.frcsty.frozenactions.util

import org.bukkit.Bukkit

object Reflection {
    private val version by lazy {
        Bukkit.getServer().javaClass.`package`.name.split(".")[3]
    }

    val is1_8: Boolean
        get() = "v1_8" in version

    val is16: Boolean
        get() = "v1_16" in version

    fun getNMSClass(className: String): Class<*> {
        return Class.forName("net.minecraft.server.$version.$className")
    }

    fun getCraftBukkitClass(className: String): Class<*> {
        return Class.forName("org.bukkit.craftbukkit.$version.$className")
    }
}