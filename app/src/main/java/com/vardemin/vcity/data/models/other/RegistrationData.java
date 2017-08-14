package com.vardemin.vcity.data.models.other;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by xavie on 14.08.2017.
 */

public class RegistrationData {
    private final String email;
    private final String name;
    private final String password;
    private final Date birth;
    private final boolean sex;

    public RegistrationData(String email, String name, String password, Date birth, boolean sex) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
    }

    public JSONObject getJSONObject() throws JSONException {
        JSONObject object = new JSONObject();
        object.put("email",email);
        object.put("password",password);
        object.put("name",name);
        object.put("birth",birth);
        object.put("sex",sex);
        return object;
    }
}
