package com.mj.sbo.objects.login;

public enum LoginType {

    APPLE("Apple"), EMAIL("Email"), FACEBOOK("Facebook"), PHONE("Phone");

    private String name;
    LoginType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
