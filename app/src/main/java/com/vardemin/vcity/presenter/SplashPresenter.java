package com.vardemin.vcity.presenter;

import android.content.Context;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.Socket;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.SplashContract;
import com.vardemin.vcity.data.local.ILocalDataRepository;
import com.vardemin.vcity.data.remote.IRemoteDataRepository;
import com.vardemin.vcity.util.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

/**
 * Created by SimUser on 24.07.2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    ILocalDataRepository localDataRepository;
    IRemoteDataRepository remoteDataRepository;

    private boolean verified = false;

    public SplashPresenter(ILocalDataRepository localDataRepository, IRemoteDataRepository remoteDataRepository) {
        this.localDataRepository = localDataRepository;
        this.remoteDataRepository = remoteDataRepository;
        try {
            this.verified = localDataRepository.verifyToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.remoteDataRepository.connect();
        this.remoteDataRepository.listenOn(Socket.EVENT_CONNECT,onConnected);

    }

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (SplashContract.View) view;
        if (!verified)
            this.view.navigateLoginScreen();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAlive() {
        return view!=null;
    }

    private Emitter.Listener onConnected = args -> {
        if (verified)
            authenticate(localDataRepository.getToken());
    };

    private void authenticate(String token) {
        HashMap<String, String>  dataMap = new HashMap<>();
        dataMap.put("strategy","jwt");
        dataMap.put("accessToken", token);
        JSONObject data = new JSONObject(dataMap);
        remoteDataRepository.emit("authenticate",data, onAuthenticated);
    }

    private Ack onAuthenticated = args -> {
        JSONObject obj = (JSONObject) args[1];
        try {
            localDataRepository.cacheToken(obj.getString("accessToken"));
            view.navigateMainScreen();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    };

}
