/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.helloservlet;

import com.mycompany.helloservlet.model.Post;
import com.mycompany.helloservlet.model.SuccessMessage;
import com.mycompany.helloservlet.model.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author ricardo
 */
public class DataAccess {

    private String getPath(String type) {
        String pathPrefix = System.getenv("OPENSHIFT_DATA_DIR");
        if (pathPrefix == null) {
            pathPrefix = "c:\\Builds";
        }
        return pathPrefix + "\\" + type + ".data";
    }

    private JSONArray getJSONArrayFromFile(String type) {

        JSONParser parser = new JSONParser();
        JSONArray array = null;
        try {
            array = (JSONArray) parser.parse(new FileReader(getPath(type)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public List<Post> getPosts(String username) {
        List<Post> posts = new ArrayList<>();
        JSONArray postsJSON = getJSONArrayFromFile("posts");

        if (postsJSON == null) {
            return null;
        } else {
            for (int i = 0; i < postsJSON.size(); i++) {
                Post post = new Post();
                JSONObject obj = (JSONObject) postsJSON.get(i);
                String postUser = (String) obj.get("username");
                post.setUsername(postUser);
                post.setMine(postUser.equals(username));
                post.setMessage((String) obj.get("message"));
                try {
                    post.setDate(MyDateParser.parse((String) obj.get("date")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                posts.add(post);
            }
        }
        return posts;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        JSONArray usersJSON = getJSONArrayFromFile("users");

        if (usersJSON == null) {
            return null;
        } else {
            for (int i = 0; i < usersJSON.size(); i++) {
                User user = new User();
                JSONObject obj = (JSONObject) usersJSON.get(i);
                user.setUsername((String) obj.get("username"));
                user.setPassword((String) obj.get("password"));
                users.add(user);
            }
        }
        return users;
    }

    private void writeData(JSONArray array, String type) {
        try {
            FileWriter file = new FileWriter(getPath(type));
            file.write(array.toJSONString());
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SuccessMessage addUser(User newUser) {
        SuccessMessage message = new SuccessMessage(true, "User created");
        try {
            // check if the username is already taken
            if (!isDuplicated(newUser)) {
                JSONArray users = getJSONArrayFromFile("users");
                JSONObject user = new JSONObject();
                user.put("username", newUser.getUsername());
                user.put("password", newUser.getPassword());
                users.add(user);
                writeData(users, "users");
            } else {
                message.message = "User already taken";
                message.success = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.message = "Error when creating new user";
            message.success = false;
        }
        return message;
    }

    public boolean isDuplicated(User user) {
        try {
            JSONArray users = getJSONArrayFromFile("users");
            for (int i = 0; i < users.size(); i++) {
                JSONObject obj = (JSONObject) users.get(i);
                if (((String) obj.get("username")).equals(user.getUsername())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public SuccessMessage login(User user) {
        SuccessMessage message = new SuccessMessage(false, "User not found");
        try {
            JSONArray users = getJSONArrayFromFile("users");
            for (int i = 0; i < users.size(); i++) {
                JSONObject obj = (JSONObject) users.get(i);
                if (((String) obj.get("username")).equals(user.getUsername())
                        && ((String) obj.get("password")).equals(user.getPassword())) {
                    message.message = "User found";
                    message.success = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.message = "Error when trying to authenticate user";
            message.success = false;
        }
        return message;
    }

    public SuccessMessage addPost(Post newPost) {
        SuccessMessage message = new SuccessMessage(true, "Post added");
        try {
            JSONArray posts = getJSONArrayFromFile("posts");
            JSONObject post = new JSONObject();
            post.put("username", newPost.getUsername());
            post.put("message", newPost.getMessage());
            post.put("date", MyDateParser.toString(newPost.getDate()));
            posts.add(post);
            writeData(posts, "posts");
        } catch (Exception e) {
            message.message = "Error when adding new post";
            message.success = false;
        }
        return message;
    }
}
