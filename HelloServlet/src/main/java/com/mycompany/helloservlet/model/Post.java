/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloservlet.model;

import com.mycompany.helloservlet.DataAccess;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ricardo
 */
public class Post {

    private String username;
    private String message;
    private Date date;
    private boolean mine;
    private DataAccess dataAccess = new DataAccess();

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SuccessMessage createPost() {
        // try to save the information
        SuccessMessage message = dataAccess.addPost(this);
        // return result
        return message;
    }

    public List<Post> getPosts(String username) {
        // get posts
        List<Post> posts = dataAccess.getPosts(username);

        // sort by date
        Collections.sort(posts, new Comparator<Post>() {
            @Override
            public int compare(Post t, Post t1) {
                return t1.getDate().compareTo(t.getDate());
            }
        });

        // return result
        return posts;
    }
}
