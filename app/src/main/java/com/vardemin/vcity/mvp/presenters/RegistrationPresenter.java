package com.vardemin.vcity.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.RegistrationContract;
import com.vardemin.vcity.data.models.other.RegistrationData;
import com.vardemin.vcity.eventbus.NavigationEvent;
import com.vardemin.vcity.eventbus.Routes;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.SocketResultListener;
import com.vardemin.vcity.mvp.views.RegistrationView;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by xavie on 08.08.2017.
 */
@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {
    public static final String TAG = "REGISTRATION_PRESENTER";
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public RegistrationPresenter() {
        App.getApplicationComponent().inject(this);
    }


    public void register(RegistrationData data) {
        try {
            remoteDataRepository.emit(new SocketResultListener() {
                @Override
                public void onError(String message) {
                    getViewState().showRegistrationError(message);
                }

                @Override
                public void onResult(JSONObject object) {
                    EventBus.getDefault().post(new NavigationEvent(Routes.REGISTRATION_SUBSCREEN,Routes.LOGIN_SUBSCREEN));
                }
            }, "users::create", data.getJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*public void onEmail(String email) {
        getViewState().setEmailField(email);
    }
    public void onPassword(String password) {
        getViewState().setPasswordField(password);
    }
    public void onName(String name) {
        getViewState().setNameField(name);
    }
    public void onBirth(String birth) {
        getViewState().setBirthField(birth);
    }
    public void onSex(boolean male){
        getViewState().setSexField(male);
    }*/

}
