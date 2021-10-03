package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class PotionEffectAction implements Action {
    private static final Map<String, PotionEffectType> EFFECTS = Arrays.stream(PotionEffectType.values())
            .collect(Collectors.toUnmodifiableMap(effect -> effect.getName().toLowerCase(), effect -> effect));

    @Override
    public String getId() {
        return "POTIONEFFECT";
    }

    @Override
    public void run(final ActionContext context) {
        final String[] args = context.getDataAsStringArray(";");
        final PotionEffectType effect = EFFECTS.get(args[0].toLowerCase());

        if (effect == null) return;

        context.getPlayer().addPotionEffect(effect.createEffect(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
    }
}
