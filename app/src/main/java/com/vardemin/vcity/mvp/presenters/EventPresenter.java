package com.vardemin.vcity.mvp.presenters;

import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * Created by xavie on 19.07.2017.
 */
@InjectViewState
public class EventPresenter extends MvpPresenter<EventView> {
    public static final String ADD_EVENT_TAG = "NEW_EVENT";
    public static final String EVENT_LIST_TAG = "EVENT_LIST";

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

    public void postEvent(String name, String description, Date date, List<Uri> photos) {

        try {
            getViewState().showLoading(true);
            JSONObject object = new JSONObject();
            object.put("userId", localDataRepository.getUserId());
            object.put("name", name);
            object.put("description", description);
            object.put("occuredAt", date);
            JSONArray location = new JSONArray();
            location.put(localDataRepository.getLocation().latitude);
            location.put(localDataRepository.getLocation().longitude);
            object.put("location", location);

            remoteDataRepository.emit(new SocketResultListener() {
                @Override
                public void onError(String message) {
                    getViewState().showLoading(false);
                    getViewState().showError(message);
                    Log.d("LOG ERROR", message);
                }

                @Override
                public void onResult(JSONObject object) {
                    try {

                        EventScheme event = new Gson().fromJson(object.getJSONObject("data").toString(), EventScheme.class);
                        if (event!=null)
                            getViewState().onEventPosted(event);
                        if (photos.size()>0) {
                            /*for(Uri uri: photos) {
                                File file = new File(uri.toString());
                                RequestBody requestBody = RequestBody.create(MultipartBody.FORM,file);
                                MultipartBody.Part body = MultipartBody.Part.createFormData("FILE",file.getName(), requestBody);
                                remoteDataRepository.uploadImage(body, localDataRepository.getToken())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(photo -> {
                                            remoteDataRepository.emit("images::create");
                                        });
                            }*/
                        }
                        getViewState().showLoading(false);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        getViewState().showLoading(false);
                    }

                }
            }, "events:create", object);

        } catch (JSONException e) {
            e.printStackTrace();
            getViewState().showLoading(false);
            Log.d("LOG ERROR", e.getLocalizedMessage());
        }
    }

}
