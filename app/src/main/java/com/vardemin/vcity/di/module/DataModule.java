package com.vardemin.vcity.di.module;

import android.content.Context;

import com.github.nkzawa.socketio.client.Socket;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.local.LocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.RemoteDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by xavie on 23.07.2017.
 */
@Module
public class DataModule {
    private Realm realm;
    public DataModule(Realm realm) {
        this.realm = realm;
    }

    @Provides
    @Singleton
    public ILocalDataRepository provideLocalDataRepositoty(Context context) {
        return new LocalDataRepository(context, realm);
    }

    @Provides
    @Singleton
    public IRemoteDataRepository provideRemoteDataRepository(Socket socket) {
        return new RemoteDataRepository(socket);
    }
}
