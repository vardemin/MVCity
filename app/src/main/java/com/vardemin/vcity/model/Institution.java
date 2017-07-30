package com.vardemin.vcity.model;

import com.vardemin.vcity.model.other.LocationNumber;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Institution extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private String description;
    private RealmList<Photo> photos;
    private RealmList<LocationNumber> location;
    private User owner;
    private RealmList<User> responsible;
}
