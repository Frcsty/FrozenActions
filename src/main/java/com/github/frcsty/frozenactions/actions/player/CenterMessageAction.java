package com.github.frcsty.frozenactions.actions.player;

import com.github.frcsty.frozenactions.actions.Action;
import com.github.frcsty.frozenactions.actions.ActionContext;
import com.github.frcsty.frozenactions.util.DefaultFontInfo;
import com.github.frcsty.frozenactions.util.Extensions;
import org.jetbrains.annotations.NotNull;

public final class CenterMessageAction implements Action {

    private static final int CENTER_PX = 154;
    private static final int MAX_PX = 250;

    @Override
    public @NotNull String getId() {
        return "CENTERMESSAGE";
    }

    @Override
    public void run(final @NotNull ActionContext context) {
        execute(context);
    }

    public static void execute(final ActionContext context) {
        String message = Extensions.colorize(context.getData());
        int messagePxSize = 0;

        boolean previousCode = false;
        boolean isBold = false;

        int charIndex = 0;
        int lastSpaceIndex = 0;
        String toSendAfter = null;
        String recentColorCode = "";

        for (char character : message.toCharArray()) {
            if (character == '\u00a7') {
                previousCode = true;
                continue;
            } else if (previousCode) {
                previousCode = false;
                recentColorCode = "§" + character;
                if (character == 'l' || character == 'L') {
                    isBold = true;
                    continue;
                } else {
                    isBold = false;
                }
            } else if (character == ' ') {
                lastSpaceIndex = charIndex;
            } else {
                final DefaultFontInfo dfi = DefaultFontInfo.getDefaultFontInfo(character);

                messagePxSize += isBold ? dfi.getBoldLength() : dfi.getLength();
                messagePxSize++;
            }

            if (messagePxSize >= MAX_PX) {
                toSendAfter = recentColorCode + message.substring(lastSpaceIndex + 1);
                message = message.substring(0, lastSpaceIndex + 1);
                break;
            }

            charIndex++;
        }

        final int halvedMessageSize = messagePxSize / 2;
        final int toCompensate = CENTER_PX - halvedMessageSize;
        final int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;

        int compensated = 0;
        final StringBuilder builder = new StringBuilder();
        while (compensated < toCompensate) {
            builder.append(" ");
            compensated += spaceLength;
        }

        context.getPlayer().sendMessage(builder.toString() + message);
        if (toSendAfter != null) {
            execute(new ActionContext(context.getPlugin(), context.getPlayer(), toSendAfter));
        }
    }
}
