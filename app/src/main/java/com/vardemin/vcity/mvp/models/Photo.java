package com.vardemin.vcity.mvp.models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Photo extends RealmObject {
    @PrimaryKey
    private String _id;
    private User user;
    private Date createdAt;
}
