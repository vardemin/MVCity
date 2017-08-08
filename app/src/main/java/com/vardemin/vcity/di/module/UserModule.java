package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.SubScreenScope;
import com.vardemin.vcity.mvp.presenters.UserPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class UserModule {
    @Provides
    @SubScreenScope
    public UserPresenter provideUserPresenter() {
        return new UserPresenter();
    }
}
