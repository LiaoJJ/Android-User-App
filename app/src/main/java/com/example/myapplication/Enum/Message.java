package com.example.myapplication.Enum;

public enum Message {
    LOGIN_SUCCESS("login success"),
    SIGNUP_SUCCESS("signup success"),
    ALREADY_EXISTED("already exist"),
    ACCOUNT_NOT_EXIST("account not exist"),
    RESET_PASSWORD_SUCCESS("reset password success"),
    PASSWORD_NOT_MATCH("password not match")
    ;


    private final String text;
    private Message(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
