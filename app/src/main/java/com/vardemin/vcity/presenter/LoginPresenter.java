package com.vardemin.vcity.presenter;

import android.util.Log;

import com.github.nkzawa.socketio.client.Ack;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.LoginContract;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.data.local.ILocalDataRepository;
import com.vardemin.vcity.data.remote.IRemoteDataRepository;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by xavie on 19.07.2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    boolean isAuthenticated = false;

    public LoginPresenter() {
        App.getApplicationComponent().inject(this);
    }

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (LoginContract.View) view;
        if(isAuthenticated)
            this.view.navigateMainScreen();
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAlive() {
        return view!=null;
    }

    @Override
    public void login(String email, String password) {
        JSONObject object = new JSONObject();
        try {
            object.put("email",email);
            object.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            view.showError(e.getLocalizedMessage());
        }
        remoteDataRepository.emit("authenticate",object,onLogin);
    }

    private Ack onLogin = args -> {
        Log.d("SOCKET IO args 0", args[0].toString());
        Log.d("SOCKET IO args 1", args[1].toString());
        //TODO: save to BD
        /*isAuthenticated = true;
        if (isViewAlive())
            view.navigateMainScreen();*/
    };
}
