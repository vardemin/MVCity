package com.vardemin.vcity.di.component;

import android.content.Context;

import com.vardemin.vcity.mvp.presenters.RegistrationPresenter;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.di.module.ApplicationModule;
import com.vardemin.vcity.di.module.DataModule;
import com.vardemin.vcity.mvp.presenters.EventPresenter;
import com.vardemin.vcity.mvp.presenters.InstitutionPresenter;
import com.vardemin.vcity.mvp.presenters.LifePresenter;
import com.vardemin.vcity.mvp.presenters.LoginPresenter;
import com.vardemin.vcity.mvp.presenters.MainPresenter;
import com.vardemin.vcity.mvp.presenters.MeetPresenter;
import com.vardemin.vcity.mvp.presenters.MeetingPresenter;
import com.vardemin.vcity.mvp.presenters.MeetingsPresenter;
import com.vardemin.vcity.mvp.presenters.MeetsPresenter;
import com.vardemin.vcity.mvp.presenters.ProfilePresenter;
import com.vardemin.vcity.mvp.presenters.SettingsPresenter;
import com.vardemin.vcity.mvp.presenters.SplashPresenter;
import com.vardemin.vcity.mvp.presenters.UserPresenter;

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
    void inject(RegistrationPresenter registrationPresenter);
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
