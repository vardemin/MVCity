package com.vardemin.vcity.di.component;

import android.content.Context;

import com.vardemin.vcity.data.local.ILocalDataRepository;
import com.vardemin.vcity.data.remote.IRemoteDataRepository;
import com.vardemin.vcity.di.module.ApplicationModule;
import com.vardemin.vcity.di.module.DataModule;
import com.vardemin.vcity.presenter.EventPresenter;
import com.vardemin.vcity.presenter.InstitutionPresenter;
import com.vardemin.vcity.presenter.LifePresenter;
import com.vardemin.vcity.presenter.LoginPresenter;
import com.vardemin.vcity.presenter.MainPresenter;
import com.vardemin.vcity.presenter.MeetPresenter;
import com.vardemin.vcity.presenter.MeetingPresenter;
import com.vardemin.vcity.presenter.MeetingsPresenter;
import com.vardemin.vcity.presenter.MeetsPresenter;
import com.vardemin.vcity.presenter.ProfilePresenter;
import com.vardemin.vcity.presenter.SettingsPresenter;
import com.vardemin.vcity.presenter.SplashPresenter;
import com.vardemin.vcity.presenter.UserPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by SimUser on 18.07.2017.
 */
@Component(modules = {ApplicationModule.class, DataModule.class})
@Singleton
public interface ApplicationComponent {
    Context context();
    ILocalDataRepository localDataRepository();
    IRemoteDataRepository remoteDataRepository();

    void inject(EventPresenter eventPresenter);
    void inject(InstitutionPresenter institutionPresenter);
    void inject(LifePresenter lifePresenter);
    void inject(LoginPresenter loginPresenter);
    void inject(MainPresenter mainPresenter);
    void inject(MeetPresenter meetPresenter);
    void inject(MeetingPresenter meetingPresenter);
    void inject(MeetingsPresenter meetingsPresenter);
    void inject(MeetsPresenter meetsPresenter);
    void inject(ProfilePresenter profilePresenter);
    void inject(SettingsPresenter settingsPresenter);
    void inject(SplashPresenter splashPresenter);
    void inject(UserPresenter userPresenter);
}
