package com.vardemin.vcity.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Interest extends RealmObject {
    @PrimaryKey
    private String name;
}
