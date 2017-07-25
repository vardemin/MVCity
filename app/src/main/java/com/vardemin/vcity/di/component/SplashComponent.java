package com.vardemin.vcity.di.component;

import com.vardemin.vcity.di.module.SplashModule;
import com.vardemin.vcity.di.scope.ScreenScope;
import com.vardemin.vcity.ui.activity.SplashActivity;

import dagger.Component;

/**
 * Created by xavie on 25.07.2017.
 */
@Component(dependencies = ApplicationComponent.class, modules = SplashModule.class)
@ScreenScope
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
