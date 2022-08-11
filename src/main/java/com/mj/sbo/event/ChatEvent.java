package com.mj.sbo.event;

public class ChatEvent extends Event{

    private String userName;
    private String message;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChatEvent(String userName, String message){
        super();
        this.userName = userName;
        this.message = message;

    }



}
