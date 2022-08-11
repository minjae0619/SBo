package com.mj.sbo.event;

public class QuitEvent  extends Event{

    private String userName;
    private String quitMessage;

    public void setQuitMessage(String quitMessage) {
        this.quitMessage = quitMessage;
    }
    public String getQuitMessage() {
        return quitMessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public QuitEvent(String userName, String quitMessage){
        super();
        this.userName = userName;
        this.quitMessage = quitMessage;
    }

}
