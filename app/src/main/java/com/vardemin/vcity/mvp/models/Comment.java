package com.vardemin.vcity.mvp.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Comment extends RealmObject {
    @PrimaryKey
    private String id;
    private User user;
    private Event event;
    private String text;
    private Date createdAt;
    private Date updatedAt;
}
