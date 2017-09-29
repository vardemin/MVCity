package com.vardemin.vcity.data.models.scheme;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by xavie on 29.09.2017.
 */

public class UserScheme {
    private String id;
    private String name;
    private String email;
    private int age;
    private boolean sex;
    private String avatar;
    private List<Float> location;
    private Date createdAt;
    private Date updatedAt;
}
