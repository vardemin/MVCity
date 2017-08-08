package com.vardemin.vcity.mvp.repositories.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.vardemin.vcity.util.JWTUtil;

import io.realm.Realm;

/**
 * Created by SimUser on 18.07.2017.
 */

public class LocalDataRepository implements ILocalDataRepository {

    private static final String APP_PREFERENCES = "mvcity_pref";
    private static final String APP_PREFERENCES_TOKEN = "access_token";

    private Realm realm;
    private Context context;

    private SharedPreferences preferences;


    public LocalDataRepository(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
        preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Override
    public String getToken() {
        return preferences.getString(APP_PREFERENCES_TOKEN, "");
    }

    @Override
    public boolean verifyToken() throws Exception {
        return getToken().equals("")? false: JWTUtil.isExpired(getToken());
    }

    @Override
    public void cacheToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(APP_PREFERENCES_TOKEN, token);
        editor.apply();
    }
}
