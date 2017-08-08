package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.SubScreenScope;
import com.vardemin.vcity.mvp.presenters.LifePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class LifeModule {
    @Provides
    @SubScreenScope
    public LifePresenter provideLifePresenter() {
        return new LifePresenter();
    }
}
