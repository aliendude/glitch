package com.example.pedro.myapplication.backend1.Model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Created by pedro on 23/07/15.
 */
@Entity
public class Coverage {

    @Id
    private Long key;

    @Index
    private String hashtag;

    @Index
    Key<User> created_by;

    private String location;

    private String date_created;

    private int nviewers;

    private int nshared;

    private String description;


    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNviewers() {
        return nviewers;
    }

    public void setNviewers(int nviewers) {
        this.nviewers = nviewers;
    }

    public int getNshared() {
        return nshared;
    }

    public void setNshared(int nshared) {
        this.nshared = nshared;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
