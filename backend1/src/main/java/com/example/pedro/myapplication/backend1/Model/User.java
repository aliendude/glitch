package com.example.pedro.myapplication.backend1.Model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mac on 23/08/15.
 */
@Entity
public class User {



    @Id
    private Long id;

    @Index
    private String username;

    private String password;

    private String name;

    private String email;

    List<Key<User>> following = new ArrayList<Key<User>>();

    List<Key<Coverage>> subscribed_coverages = new ArrayList<Key<Coverage>>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;

    }


}
