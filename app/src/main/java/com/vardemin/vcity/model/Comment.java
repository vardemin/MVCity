package com.vardemin.vcity.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Comment extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String email;
    private int age;
    private boolean sex;
    private String avatar;
    
}
