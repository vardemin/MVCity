package com.vardemin.vcity.mvp.repositories.remote;

import org.json.JSONObject;

/**
 * Created by xavie on 14.08.2017.
 */

public interface SocketResultListener {
    void onError(String message);
    void onResult(JSONObject object);
}
