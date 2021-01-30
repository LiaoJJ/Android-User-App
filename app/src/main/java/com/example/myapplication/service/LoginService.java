package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.Enum.Message;
import com.example.myapplication.dao.UserDAO;

public class LoginService {
    UserDAO userDao;
    public LoginService(Context context){
        this.userDao = new UserDAO(context);
    }

    public Message login(String email, String password){
        String expectedPassword = userDao.queryPasswordByEmail(email);
        if (null==expectedPassword){
            return Message.ACCOUNT_NOT_EXIST;
        }
        if(expectedPassword.equals(password)){
            return Message.LOGIN_SUCCESS;
        }
        else{
            return Message.PASSWORD_NOT_MATCH;
        }
    }
}
