package com.vardemin.vcity.di.module;

import com.vardemin.vcity.data.local.ILocalDataRepository;
import com.vardemin.vcity.data.remote.IRemoteDataRepository;
import com.vardemin.vcity.di.scope.ScreenScope;
import com.vardemin.vcity.presenter.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by SimUser on 24.07.2017.
 */
@Module
public class SplashModule {
    @Provides
    @ScreenScope
    public SplashPresenter provideSplashPresenter(ILocalDataRepository localDataRepository, IRemoteDataRepository remoteDataRepository) {
        return new SplashPresenter(localDataRepository, remoteDataRepository);
    }
}
