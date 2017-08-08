package com.vardemin.vcity.di.component;

import com.vardemin.vcity.di.module.LoginModule;
import com.vardemin.vcity.di.scope.ScreenScope;
import com.vardemin.vcity.ui.activity.LoginActivity;
import com.vardemin.vcity.ui.fragment.RegistrationFragment;

import dagger.Component;

/**
 * Created by xavie on 25.07.2017.
 */
@Component(dependencies = ApplicationComponent.class, modules = LoginModule.class)
@ScreenScope
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
    void inject(RegistrationFragment registrationFragment);
}
