package com.mj.sbo.objects.login;

public abstract class Login {
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    abstract public void login();

    abstract public LoginType getLoginType();
}
