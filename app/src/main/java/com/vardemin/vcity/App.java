package com.vardemin.vcity;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.vardemin.vcity.di.component.ApplicationComponent;
import com.vardemin.vcity.di.component.DaggerApplicationComponent;
import com.vardemin.vcity.di.module.ApplicationModule;
import com.vardemin.vcity.di.module.DataModule;
import com.vardemin.vcity.di.module.RestModule;
import com.vardemin.vcity.util.Constants;

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
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetworkStateReceiver(), intentFilter);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule(realm))
                .restModule(new RestModule(Constants.SERVER_URL, false))
                .build();
    }
}