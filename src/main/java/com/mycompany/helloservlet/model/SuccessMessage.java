/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloservlet.model;

/**
 *
 * @author ricardo
 */
public class SuccessMessage {
    public boolean success;
    public String message;
    
    public SuccessMessage(boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
