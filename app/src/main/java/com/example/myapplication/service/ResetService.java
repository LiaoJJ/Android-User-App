package com.example.myapplication.service;

import android.content.Context;

import com.example.myapplication.Entity.User;
import com.example.myapplication.Enum.Message;
import com.example.myapplication.dao.UserDAO;

public class ResetService {
    UserDAO userDao;
    public ResetService(Context context){
        this.userDao = new UserDAO(context);
    }

    public Message reset(String email, String password){
        String expectedPassword = userDao.queryPasswordByEmail(email);
        if(expectedPassword==null){
            return Message.ACCOUNT_NOT_EXIST;
        }
        userDao.update(new User(email, password));
        return Message.RESET_PASSWORD_SUCCESS;
    }
}
