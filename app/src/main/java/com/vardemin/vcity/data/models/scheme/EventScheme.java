package com.vardemin.vcity.data.models.scheme;


import java.util.Date;
import java.util.List;


/**
 * Created by xavie on 29.09.2017.
 */

public class EventScheme{
    private String id;
    private String name;
    private String description;
    private UserScheme user;
    private Date occuredAt;
    private List<PhotoScheme> photos;
    private List<Float> location;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UserScheme getUser() {
        return user;
    }

    public Date getOccuredAt() {
        return occuredAt;
    }

    public List<PhotoScheme> getPhotos() {
        return photos;
    }

    public List<Float> getLocation() {
        return location;
    }

    public void setUser(UserScheme user) {
        this.user = user;
    }

    public void setPhotos(List<PhotoScheme> photos) {
        this.photos = photos;
    }

    public void setLocation(List<Float> location) {
        this.location = location;
    }
}
