package com.github.frcsty.frozenactions.actions;

import org.jetbrains.annotations.NotNull;

public interface Action {

    @NotNull String getId();

    default void run(final @NotNull ActionContext context) { }

}
