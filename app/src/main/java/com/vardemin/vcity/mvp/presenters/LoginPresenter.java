package com.vardemin.vcity.mvp.presenters;

import android.util.Log;

import com.github.nkzawa.socketio.client.Ack;
import com.vardemin.vcity.contract.LoginContract;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xavie on 19.07.2017.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    private ILocalDataRepository localDataRepository;
    private IRemoteDataRepository remoteDataRepository;

    boolean isAuthenticated = false;

    public LoginPresenter(ILocalDataRepository localDataRepository, IRemoteDataRepository remoteDataRepository) {
        this.localDataRepository = localDataRepository;
        this.remoteDataRepository = remoteDataRepository;
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
            object.put("strategy","local");
            object.put("email",email);
            object.put("password", password);
            Log.d("SOCKET SENT: ", object.toString());
            remoteDataRepository.emit("authenticate",object,onLogin);
        } catch (JSONException e) {
            e.printStackTrace();
            view.showError(e.getLocalizedMessage());
        }

    }

    private Ack onLogin = args -> {
        for (int i=0; i<args.length; i++) {
            if(args[i]!=null) {
                if (i == 0) {
                    try {
                        view.showLoginError(((JSONObject) args[i]).getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (i == 1)
                    try {
                        localDataRepository.cacheToken(((JSONObject) args[i]).getString("accessToken"));
                        isAuthenticated = true;
                        view.navigateMainScreen();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                Log.d("SOCKET IO args", args[i].toString());
            }
        }

        //TODO: save to BD
        /*isAuthenticated = true;
        if (isViewAlive())
            view.navigateMainScreen();*/
    };
}
