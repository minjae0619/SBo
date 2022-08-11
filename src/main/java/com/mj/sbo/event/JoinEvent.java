package com.mj.sbo.event;

public class JoinEvent extends Event{

    private String userName;


    private String joinMessage;

    public void setJoinMessage(String joinMessage) {
        this.joinMessage = joinMessage;
    }
    public String getJoinMessage() {
        return joinMessage;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public JoinEvent(String userName, String joinMessage){
        super();
        this.userName = userName;
        this.joinMessage = joinMessage;
    }
}
