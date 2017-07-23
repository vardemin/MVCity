package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.SubScreenScope;
import com.vardemin.vcity.presenter.EventPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class EventModule {
    @Provides
    @SubScreenScope
    public EventPresenter provideEventPresenter() {
        return new EventPresenter();
    }
}
