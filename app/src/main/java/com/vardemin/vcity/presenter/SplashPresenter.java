package com.vardemin.vcity.presenter;

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
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public SplashPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (SplashContract.View) view;
        if (localDataRepository.verifyToken()){
            if(NetworkUtil.isNetworkConnected(App.getApplicationComponent().context())) {
                remoteDataRepository.connect();
            }
            this.view.navigateMainScreen();
        }
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
        if (localDataRepository.verifyToken())
            authenticate(localDataRepository.getToken());
        else {
            view.navigateLoginScreen();
        }
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
