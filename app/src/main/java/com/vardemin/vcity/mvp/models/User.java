package com.vardemin.vcity.mvp.models;

import com.vardemin.vcity.mvp.models.other.LocationNumber;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class User extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String email;
    private int age;
    private boolean sex;
    private String avatar;
    private RealmList<LocationNumber> location;
    private Date createdAt;
    private Date updatedAt;
}
