package com.vardemin.vcity.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.vardemin.vcity.util.Constants;

import java.net.URISyntaxException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.socket.client.IO;
import io.socket.client.Socket;

/**
 * Created by SimUser on 18.07.2017.
 */

@Module
public class ApplicationModule {
    @NonNull
    private final Context context;
    private Socket socket;
    {
        try {
            socket = IO.socket(Constants.SERVER_URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public ApplicationModule(@NonNull Context context) {
        this.context = context;
    }

    /**
     * Provide Application context
     * @return context
     */
    @Provides
    @Singleton
    Context provideContext() {
        return this.context;
    }

    /**
     * Provide Socket.IO client
     * @return socket
     */
    @Provides
    @Singleton
    Socket provideSocket() {
        return this.socket;
    }
}
