package com.mj.sbo.event;

public class SpoonEvent extends Event{

    private String userName;
    private String spoonMessage;
    private int spoon;

    public String getUserName() {
        return userName;
    }

    public void setSpoonMessage(String spoonMessage) {
        this.spoonMessage = spoonMessage;
    }

    public String getSpoonMessage() {
        return spoonMessage;
    }

    public int getSpoon() {
        return spoon;
    }


    public SpoonEvent(String userName, int spoon,  String spoonMessage){
        super();
        this.userName = userName;
        this.spoon = spoon;
        this.spoonMessage = spoonMessage;
    }

}
