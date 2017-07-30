package com.vardemin.vcity.data.local;

/**
 * Created by SimUser on 18.07.2017.
 */

public interface ILocalDataRepository {
    String getToken();
    boolean verifyToken() throws Exception;
    void cacheToken(String token);
}
