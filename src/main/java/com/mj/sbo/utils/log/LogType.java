package com.mj.sbo.utils.log;

public enum LogType {
    COMMAND("Command"),
    QUIT("Quit"),
    JOIN("Join"),
    SPOON("Spoon"),
    LIKE("Like"),
    CHAT("Chat"),
    DEBUG("Debug"),
    WARN("Warning"),
    ERROR("Error"),
    INFO("Info");

    private String message;
    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
