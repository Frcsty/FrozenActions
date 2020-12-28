package com.github.frcsty.frozenactions.util;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class Titles {

    private static final Class<?> ENTITY_PLAYER_CLASS = Reflection.getNMSClass("EntityPlayer");
    private static final Class<?> PLAYER_CONNECTION_CLASS = Reflection.getNMSClass("PlayerConnection");
    private static final Class<?> CRAFT_PLAYER_CLASS = Reflection.getCraftBukkitClass("entity.CraftPlayer");
    private static final Class<?> PACKET_CLASS = Reflection.getNMSClass("Packet");
    private static final Class<?> ENUM_TITLE_ACTION_CLASS = Reflection.getNMSClass("PacketPlayOutTitle\\$EnumTitleAction");
    private static final Class<?> I_CHAT_BASE_COMPONENT_CLASS = Reflection.getNMSClass("IChatBaseComponent");
    private static final Class<?> PACKET_PLAY_OUT_CHAT_CLASS = Reflection.getNMSClass("PacketPlayOutChat");

    private static final Constructor PACKET_PLAY_OUT_TITLE_CONSTRUCTOR = getPacketPlayOutTitleConstructor();
    private static final Constructor PACKET_PLAY_OUT_CHAT_CONSTRUCTOR = getPacketPlayOutChatConstructor();

    private static final Method SEND_PACKET_METHOD = getSendPacketMethod();
    private static final Method HANDLE_METHOD = getHandleMethod();
    private static final Method CHAT_SERIALIZER_METHOD = getChatSerializerMethod();

    private static final Field PLAYER_CONNECTION_FIELD = getPlayerConnectionField();

    private static final Object TITLE_ENUM = getTitleEnum();
    private static final Object SUB_TITLE_ENUM = getSubTitleEnum();

    private static Constructor getPacketPlayOutTitleConstructor() {
        try {
            return Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(ENUM_TITLE_ACTION_CLASS, I_CHAT_BASE_COMPONENT_CLASS, Integer.class, Integer.class, Integer.class);
        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(String.format("Failed to retrieve Constructor from '%s' Class on version '%s'", "PacketPlayOutTitle", Reflection.VERSION));
        }
    }

    private static Constructor getPacketPlayOutChatConstructor() {
        try {
            return Reflection.getNMSClass("PacketPlayOutChat").getConstructor(PACKET_PLAY_OUT_CHAT_CLASS, I_CHAT_BASE_COMPONENT_CLASS, Byte.class);
        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(String.format("Failed to retrieve Constructor from '%s' Class on version '%s'", "PacketPlayOutChat", Reflection.VERSION));
        }
    }

    private static Method getChatSerializerMethod() {
        try {
            return Reflection.getNMSClass("IChatBaseComponent\\$ChatSerializer").getMethod("a", String.class);
        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Method from '%s' Class on version '%s'", "a", "IChatBaseComponent\\$ChatSerializer", Reflection.VERSION));
        }
    }

    private static Method getHandleMethod() {
        try {
            return CRAFT_PLAYER_CLASS.getMethod("getHandle");
        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Method from '%s' Class on version '%s'", "getHandle", "entity.CraftPlayer", Reflection.VERSION));
        }
    }

    private static Method getSendPacketMethod() {
        try {
            return PLAYER_CONNECTION_CLASS.getMethod("sendPacket", PACKET_CLASS);
        } catch (final NoSuchMethodException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Method from '%s' Class on version '%s'", "sendPacket", "Packet", Reflection.VERSION));
        }
    }

    private static Field getPlayerConnectionField() {
        try {
            return ENTITY_PLAYER_CLASS.getField("playerConnection");
        } catch (final NoSuchFieldException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Field from '%s' Class on version '%s'", "playerConnection", "EntityPlayer", Reflection.VERSION));
        }
    }

    private static Object getTitleEnum() {
        try {
            return ENUM_TITLE_ACTION_CLASS.getField("TITLE").get(null);
        } catch (final NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Field from '%s' Class on version '%s'", "TITLE", "PacketPlayOutTitle\\$EnumTitleAction", Reflection.VERSION));
        }
    }

    private static Object getSubTitleEnum() {
        try {
            return ENUM_TITLE_ACTION_CLASS.getField("SUBTITLE").get(null);
        } catch (final NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException(String.format("Failed to retrieve '%s' Field from '%s' Class on version '%s'", "SUBTITLE", "PacketPlayOutTitle\\$EnumTitleAction", Reflection.VERSION));
        }
    }

    private static Object createIChatBaseComponent(final String text) {
        try {
            return CHAT_SERIALIZER_METHOD.invoke(null, String.format("{\"text\":\"%s\"}", text));
        } catch (final IllegalAccessException | InvocationTargetException ex) {
            throw new RuntimeException(String.format("Failed to invoke Text Method from '%s' Class on version '%s'", "IChatBaseComponent\\$ChatSerializer", Reflection.VERSION));
        }
    }

    private static void sendPacket(final Player player, final Object packet) {
        try {
            final Object entityPlayer = ENTITY_PLAYER_CLASS.cast(HANDLE_METHOD.invoke(player));
            final Object playerConnection = PLAYER_CONNECTION_FIELD.get(entityPlayer);

            SEND_PACKET_METHOD.invoke(playerConnection, packet);
        } catch (final IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }

    public static void sendPlayerTitle(final Player player, final String title, final String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (Reflection.is1_8()) {
            try {
                final Object titleBaseComponent = createIChatBaseComponent(title);
                final Object titlePacket = PACKET_PLAY_OUT_TITLE_CONSTRUCTOR.newInstance(TITLE_ENUM, titleBaseComponent, fadeIn, stay, fadeOut);

                sendPacket(player, titlePacket);
                if (subtitle != null) {
                    final Object subTitleBaseComponent = createIChatBaseComponent(subtitle);
                    final Object subTitlePacket = PACKET_PLAY_OUT_TITLE_CONSTRUCTOR.newInstance(SUB_TITLE_ENUM, subTitleBaseComponent, fadeIn, stay, fadeOut);

                    sendPacket(player, subTitlePacket);
                }
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        } else {
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    public static void sendActionBarMessage(final Player player, final String message) {
        if (Reflection.is1_8()) {
            try {
                final Object actionBarBaseComponent = createIChatBaseComponent(message);
                final Object actionBarPacket = PACKET_PLAY_OUT_CHAT_CONSTRUCTOR.newInstance(actionBarBaseComponent);

                sendPacket(player, actionBarPacket);
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        } else {
            player.sendActionBar(message);
        }
    }

}
