package com.vardemin.vcity.di.module;

import com.vardemin.vcity.di.scope.SubScreenScope;
import com.vardemin.vcity.mvp.presenters.InstitutionPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class InstitutionModule {
    @Provides
    @SubScreenScope
    public InstitutionPresenter provideInstitutionPresenter() {
        return new InstitutionPresenter();
    }
}
