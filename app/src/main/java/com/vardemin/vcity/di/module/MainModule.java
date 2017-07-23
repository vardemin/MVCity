package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.ScreenScope;
import com.vardemin.vcity.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class MainModule {
    @Provides
    @ScreenScope
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
