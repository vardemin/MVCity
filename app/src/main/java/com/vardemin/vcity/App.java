package com.vardemin.vcity;

import android.app.Application;

import com.vardemin.vcity.di.component.ApplicationComponent;
import com.vardemin.vcity.di.component.DaggerApplicationComponent;
import com.vardemin.vcity.di.component.DaggerLoginComponent;
import com.vardemin.vcity.di.component.DaggerSplashComponent;
import com.vardemin.vcity.di.component.LoginComponent;
import com.vardemin.vcity.di.component.SplashComponent;
import com.vardemin.vcity.di.module.ApplicationModule;
import com.vardemin.vcity.di.module.DataModule;
import com.vardemin.vcity.di.module.LoginModule;
import com.vardemin.vcity.di.module.SplashModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;



public class App extends Application {
    private Realm realm;

    private static ApplicationComponent applicationComponent;
    private static SplashComponent splashComponent;
    private static LoginComponent loginComponent;

    /**
     * DI Application Component provider
     * @return app component
     */
    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static SplashComponent getSplashComponent() {
        return splashComponent;
    }

    public static LoginComponent getLoginComponent() {
        return loginComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule(realm))
                .build();
        splashComponent = DaggerSplashComponent.builder()
                .applicationComponent(applicationComponent)
                .splashModule(new SplashModule())
                .build();
        loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(applicationComponent)
                .loginModule(new LoginModule())
                .build();
    }
}