package com.mj.sbo.event;

public class Event {

    private boolean cancelled;

    public Event(){
        this.cancelled = false;
    }


    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
