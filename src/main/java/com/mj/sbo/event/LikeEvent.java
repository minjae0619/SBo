package com.mj.sbo.event;

public class LikeEvent extends Event{


    private String userName;

    public LikeEvent(String userName) {
        super();
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
