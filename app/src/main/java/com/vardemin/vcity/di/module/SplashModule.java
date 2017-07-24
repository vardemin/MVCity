package com.vardemin.vcity.di.module;

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
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenter();
    }
}
