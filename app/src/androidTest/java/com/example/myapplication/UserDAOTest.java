package com.example.myapplication;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.Entity.User;
import com.example.myapplication.dao.UserDAO;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UserDAOTest {

    @Test
    public void addTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO userDAO = new UserDAO(context);
        userDAO.add(new User("admin", "admin"));
    }

    @Test
    public void queryTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO userDAO = new UserDAO(context);
        assertEquals("admin", userDAO.queryPasswordByEmail("admin"));
    }

    @Test
    public void updateTest(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        UserDAO userDAO = new UserDAO(context);
        userDAO.update(new User("admin", "22222"));
        assertEquals("22222", userDAO.queryPasswordByEmail("admin"));
        userDAO.update(new User("admin", "admin"));
    }
}
