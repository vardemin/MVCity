package com.vardemin.vcity.model;

import com.vardemin.vcity.model.other.LocationNumber;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Meeting extends RealmObject {
    private String name;
    private String description;
    private RealmList<LocationNumber> location;
    private int priority;
    private Institution institution;
    private Date when;
    private Date end;
    private RealmList<Interest> interests;

}
