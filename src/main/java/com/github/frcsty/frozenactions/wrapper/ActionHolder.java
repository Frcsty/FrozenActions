package com.github.frcsty.frozenactions.wrapper;

import org.jetbrains.annotations.NotNull;

public record ActionHolder(@NotNull String action, @NotNull Long delay) {

    public @NotNull String getAction() {
        return this.action;
    }

    public @NotNull Long getDelay() {
        return this.delay;
    }

}
