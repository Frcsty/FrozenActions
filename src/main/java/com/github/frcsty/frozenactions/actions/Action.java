package com.github.frcsty.frozenactions.actions;

public interface Action {

    String getId();

    default void run(final ActionContext context) { }

}
