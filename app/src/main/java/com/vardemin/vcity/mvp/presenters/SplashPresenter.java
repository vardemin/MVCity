package com.vardemin.vcity.mvp.presenters;

import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.SplashContract;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.SocketResultListener;
import com.vardemin.vcity.mvp.views.SplashView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

import io.socket.client.Ack;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by SimUser on 24.07.2017.
 */
@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    private boolean verified = false;


    public SplashPresenter() {
        App.getApplicationComponent().inject(this);
        try {
            this.verified = localDataRepository.verifyToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.remoteDataRepository.connect();
        this.remoteDataRepository.listenOn(Socket.EVENT_CONNECT,onConnected);
        this.remoteDataRepository.listenOn(Socket.EVENT_CONNECT_TIMEOUT, onConnectionFailed);
        this.remoteDataRepository.listenOn(Socket.EVENT_CONNECT_ERROR, onConnectionFailed);

    }
    @Override
    public void attachView(SplashView view) {
        super.attachView(view);
        if(remoteDataRepository.isConnected()) {
            if (verified)
                authenticate(localDataRepository.getToken());
            else getViewState().setAuthorized(false);
        }
    }

 /*   @Override
    public void attachView(SplashView view) {
        try {
            view.setAuthorized(localDataRepository.verifyToken());
        } catch (Exception e) {
            e.printStackTrace();
            view.setAuthorized(false);
        }
    }*/


    private Emitter.Listener onConnected = args -> {
        if (verified)
            authenticate(localDataRepository.getToken());
        else getViewState().setAuthorized(false);
    };

    private Emitter.Listener onConnectionFailed = args -> {
        getViewState().showError("Connection failed. Check your Internet connection!");
    };

    private void authenticate(String token) {
        HashMap<String, String>  dataMap = new HashMap<>();
        dataMap.put("strategy","jwt");
        dataMap.put("accessToken", token);
        JSONObject data = new JSONObject(dataMap);
        remoteDataRepository.emit(new SocketResultListener() {
            @Override
            public void onError(String message) {
                Log.d("Auth", message);
                getViewState().setAuthorized(false);
            }

            @Override
            public void onResult(JSONObject object) {
                try {
                    localDataRepository.cacheToken(object.getString("accessToken"));
                    getViewState().setAuthorized(true);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },"authenticate", data);
    }

    private Ack onAuthenticated = args -> {
        JSONObject obj = (JSONObject) args[1];
        try {
            localDataRepository.cacheToken(obj.getString("accessToken"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

}
