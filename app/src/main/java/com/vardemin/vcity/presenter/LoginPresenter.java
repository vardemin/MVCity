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
            if(args[i]!=null)
            Log.d("SOCKET IO args", args[i].toString());
        }

        //TODO: save to BD
        /*isAuthenticated = true;
        if (isViewAlive())
            view.navigateMainScreen();*/
    };
}
