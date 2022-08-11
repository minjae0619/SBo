package com.mj.sbo.event;

public class CommandEvent extends Event{


    private String command;
    private String userName;
    private boolean basic;

    public CommandEvent(String userName, String command) {
        super();
        this.command = command;
        this.userName = userName;
        this.basic = command.startsWith("!ps")||command.startsWith("!pa")||command.startsWith("!next") ||
                command.startsWith("!n") ||command.startsWith("!pr")||command.startsWith("!신청곡")||
                command.startsWith("!pl")||command.startsWith("!플레이리스트") || command.startsWith("!플리")||command.startsWith("!현재곡")||
                command.startsWith("!추첨") || command.startsWith("!룰렛");
    }

    public boolean isBasic() {
        return basic;
    }

    public void setBasic(boolean basic) {
        this.basic = basic;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
