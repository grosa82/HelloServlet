/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloservlet.model;

import com.mycompany.helloservlet.DataAccess;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class User {
    private String username;
    private String password;
    private DataAccess dataAccess = new DataAccess();
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(){
        
    }
    
    public SuccessMessage createUsername() {
        // try to save the information
        SuccessMessage message = dataAccess.addUser(this);
        // return result
        return message;
    }
    
    public SuccessMessage login() {
        // try to login
        SuccessMessage message = dataAccess.login(this);
        // return result
        return message;
    }
    
}
