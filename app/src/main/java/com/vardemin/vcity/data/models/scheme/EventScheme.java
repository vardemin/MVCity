package com.vardemin.vcity.data.models.scheme;


import com.google.android.gms.maps.model.LatLng;

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
    private List<Double> location;

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

    public List<Double> getLocation() {
        return location;
    }

    public void setUser(UserScheme user) {
        this.user = user;
    }

    public void setPhotos(List<PhotoScheme> photos) {
        this.photos = photos;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public LatLng getLatLng() {
        if (location.size()>1)
            return new LatLng(location.get(0), location.get(1));
        else return new LatLng(0,0);
    }
}
