package com.github.frcsty.frozenactions.wrapper;

public final class ActionHolder {

    private final String action;
    private final Long delay;

    public ActionHolder(final String action, final Long delay) {
        this.action = action;
        this.delay = delay;
    }

    public String getAction() {
        return this.action;
    }

    public Long getDelay() {
        return this.delay;
    }

}
