package com.vardemin.vcity.data.models.scheme;


import java.util.Date;
import java.util.List;


/**
 * Created by xavie on 29.09.2017.
 */

public class EventScheme {
    private String id;
    private String name;
    private String description;
    private UserScheme user;
    private Date occuredAt;
    private List<PhotoScheme> photos;
    private List<Float> location;
}
