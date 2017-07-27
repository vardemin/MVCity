package com.vardemin.vcity;

import android.app.Application;

import com.vardemin.vcity.di.component.ApplicationComponent;
import com.vardemin.vcity.di.component.DaggerApplicationComponent;
import com.vardemin.vcity.di.module.ApplicationModule;
import com.vardemin.vcity.di.module.DataModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;



public class App extends Application {
    private Realm realm;

    private static ApplicationComponent applicationComponent;

    /**
     * DI Application Component provider
     * @return app component
     */
    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
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
    }

}