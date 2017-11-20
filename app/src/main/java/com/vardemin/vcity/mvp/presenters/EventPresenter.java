package com.vardemin.vcity.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vardemin.vcity.App;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.SocketResultListener;
import com.vardemin.vcity.mvp.views.EventView;
import com.vardemin.vcity.util.JSONUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.inject.Inject;


/**
 * Created by xavie on 19.07.2017.
 */
@InjectViewState
public class EventPresenter extends MvpPresenter<EventView> {
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public EventPresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void addEvent(String name, String description, String date, boolean useCurrentLocation) {

        //remoteDataRepository.emit("events::create",);
    }

    public void callEventList() {
        try {
            getViewState().showLoading(true);
            LatLng loc = localDataRepository.getLocation();
            Log.d("LOG", String.valueOf(loc.latitude) + " ; " + String.valueOf(loc.longitude));
            JSONObject jsonObject = new JSONObject();
            //JSONUtil.setNear(jsonObject,loc,1000);

            jsonObject.put("location", new JSONObject("{ $nearSphere: ["+String.valueOf(loc.latitude)+","+String.valueOf(loc.longitude)+"], $maxDistance: 1000 }"));
            Log.d("LOG JSON", jsonObject.toString());
            remoteDataRepository.emit(new SocketResultListener() {

                @Override
                public void onError(String message) {
                    Log.d("LOG", message);
                    getViewState().showLoading(false);
                }

                @Override
                public void onResult(JSONObject object) {
                    getViewState().showLoading(false);
                    Log.d("LOG", object.toString());
                    try {
                        Type listType = new TypeToken<ArrayList<EventScheme>>(){}.getType();

                        getViewState().onEventList(new Gson().fromJson(object.getJSONArray("data").toString(),listType));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, "events::find", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
