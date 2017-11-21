package com.vardemin.vcity.mvp.repositories.local;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by SimUser on 18.07.2017.
 */

public interface ILocalDataRepository {

    String getToken();
    boolean verifyToken() throws Exception;
    void cacheToken(String token);

    String getUserId();

    String getLogin();
    void cacheLogin(String login);

    String getPassword();
    void cachePassword(String password);

    LatLng getLocation();
    void setLatLng(LatLng location);
    void cacheLatLng();
}
