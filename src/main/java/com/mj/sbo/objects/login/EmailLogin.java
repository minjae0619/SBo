package com.mj.sbo.objects.login;

public class EmailLogin extends Login{
    @Override
    public void login() {
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.EMAIL;
    }
}
