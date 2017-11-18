package com.vardemin.vcity.util;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xavie on 07.08.2017.
 */

public class JSONUtil {

    public static void setNear(JSONObject obj, LatLng loc, int radius) throws JSONException {
        JSONObject near = new JSONObject();
        JSONObject geometry = new JSONObject();
        JSONArray coordinates = new JSONArray();
        coordinates.put(loc.latitude);
        coordinates.put(loc.longitude);
        geometry.put("type","Point");
        geometry.put("coordinates", coordinates);
        near.put("$geometry", geometry);
        near.put("$maxDistance", radius);
        obj.put("$near", near);
    }

}
