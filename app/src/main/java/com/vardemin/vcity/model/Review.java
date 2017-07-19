package com.vardemin.vcity.model;

import java.util.Date;

import io.realm.RealmObject;


public class Review extends RealmObject {
    private User user;
    private Institution institution;
    private String text;
    private int mark;
    private Date createdAt;
}
