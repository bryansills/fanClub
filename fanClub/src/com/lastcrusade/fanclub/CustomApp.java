package com.lastcrusade.fanclub;

import com.lastcrusade.fanclub.model.UserList;

import android.app.Application;

public class CustomApp extends Application {
    private UserList userList;

    public CustomApp() {
        super();
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        userList = new UserList(); //Remember the default includes made up people
    }
    
    public UserList getUserList(){
        return userList;
    }

}