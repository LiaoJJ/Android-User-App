package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.Entity.User;
import com.example.myapplication.Enum.Message;
import com.example.myapplication.dao.UserDAO;

public class SignupService {

    UserDAO userDao;
    public SignupService(Context context){
        this.userDao = new UserDAO(context);
    }

    public Message signup(String email, String password){
        String expectedPassword = userDao.queryPasswordByEmail(email);
        if(null!=expectedPassword){
            return Message.ALREADY_EXISTED;
        }
        userDao.add(new User(email, password));
        return Message.SIGNUP_SUCCESS;
    }
}
