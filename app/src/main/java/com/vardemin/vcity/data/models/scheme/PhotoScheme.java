package com.vardemin.vcity.data.models.scheme;

import java.util.Date;

/**
 * Created by xavie on 29.09.2017.
 */

public class PhotoScheme {
    private String _id;
    private String path;
    private UserScheme user;
    private Date createdAt;

    public String getPath() {
        return path;
    }

    public UserScheme getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String get_id() {
        return _id;
    }
}
