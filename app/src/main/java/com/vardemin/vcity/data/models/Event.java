package com.vardemin.vcity.data.models;

import com.vardemin.vcity.data.models.other.LocationNumber;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Event extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String description;
    private User user;
    private Date occuredAt;
    private RealmList<Photo> photos;
    private RealmList<LocationNumber> location;
}
