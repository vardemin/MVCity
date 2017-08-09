package com.vardemin.vcity.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.nkzawa.socketio.client.Ack;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.LoginContract;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.views.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by xavie on 19.07.2017.
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {


    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    boolean isAuthenticated = false;

    public LoginPresenter() {
        App.getApplicationComponent().inject(this);
    }


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
            getViewState().showError(e.getLocalizedMessage());
        }

    }

    private Ack onLogin = args -> {
        for (int i=0; i<args.length; i++) {
            if(args[i]!=null) {
                if (i == 0) {
                    try {
                        getViewState().showLoginError(((JSONObject) args[i]).getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (i == 1)
                    try {
                        localDataRepository.cacheToken(((JSONObject) args[i]).getString("accessToken"));
                        isAuthenticated = true;
                        getViewState().navigateMainScreen();
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
