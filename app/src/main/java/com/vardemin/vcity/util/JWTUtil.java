package com.vardemin.vcity.util;

import android.util.Base64;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by xavie on 29.07.2017.
 */

public class JWTUtil {
    public static String decoded(String JWTEncoded) throws Exception {
        String[] split = JWTEncoded.split("\\.");
        return getJson(split[1]);
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }

    public static boolean isExpired(String JWTEncoded) throws Exception {
        JSONObject obj = new JSONObject(decoded(JWTEncoded));
        Date expirationDate = new Date(obj.getLong("exp"));
        return  (new Date()).after(expirationDate);
    }

    public static String getStringFromBody(String JWTEncoded, String key) throws Exception {
        JSONObject obj = new JSONObject(decoded(JWTEncoded));
        return obj.getString(key);
    }
}
