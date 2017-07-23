package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.SubScreenScope;
import com.vardemin.vcity.presenter.SettingsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class SettingsModule {
    @Provides
    @SubScreenScope
    public SettingsPresenter provideSettingsPresenter() {
        return new SettingsPresenter();
    }
}
