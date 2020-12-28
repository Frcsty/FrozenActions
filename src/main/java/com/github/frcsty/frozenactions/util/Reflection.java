package com.github.frcsty.frozenactions.util;

import org.bukkit.Bukkit;

public final class Reflection {

    static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];

    public static boolean is1_8() {
        return VERSION.contains("v1_8");
    }

    public static boolean is1_16() {
        return VERSION.contains("v1_16");
    }

    public static Class<?> getNMSClass(final String className) {
        try {
            return Class.forName(String.format("net.minecraft.server.%s.%s", VERSION, className));
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        throw new RuntimeException(String.format("Failed to retrieve NMS Class '%s' on version '%s'", className, VERSION));
    }

    public static Class<?> getCraftBukkitClass(final String className) {
        try {
            return Class.forName(String.format("org.bukkit.craftbukkit.%s.%s", VERSION, className));
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        throw new RuntimeException(String.format("Failed to retrieve Craft Bukkit Class '%s' on version '%s'", className, VERSION));
    }

}
