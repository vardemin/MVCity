package com.vardemin.vcity.di.module;

import com.vardemin.vcity.data.local.ILocalDataRepository;
import com.vardemin.vcity.data.remote.IRemoteDataRepository;
import com.vardemin.vcity.di.scope.ScreenScope;
import com.vardemin.vcity.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */

@Module
public class LoginModule {
    @Provides
    @ScreenScope
    public LoginPresenter provideLoginPresenter(ILocalDataRepository localDataRepository, IRemoteDataRepository remoteDataRepository) {
        return new LoginPresenter(localDataRepository, remoteDataRepository);
    }

}

