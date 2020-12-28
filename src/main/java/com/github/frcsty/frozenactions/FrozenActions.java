package com.github.frcsty.frozenactions;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.wrapper.ActionHandler;
import me.mattstudios.mfmsg.base.MessageOptions;
import me.mattstudios.mfmsg.base.internal.Format;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class FrozenActions extends JavaPlugin {

    @Override
    public void onEnable() {
        final ActionHandler handler = new ActionHandler(this);

        handler.loadDefaults(false);

        handler.createBukkitMessage(MessageOptions.builder()
                .removeFormat(Format.ITALIC)
                .build()
        );

        handler.setAction(new Action() {
            @Override
            public String getId() {
                return "time";
            }

            @Override
            public void run(final ActionContext context) {
                final World world = context.getPlayer().getWorld();

                world.setTime(context.getDataAsInt());
            }
        });

        handler.removeAction("time");
    }

}
