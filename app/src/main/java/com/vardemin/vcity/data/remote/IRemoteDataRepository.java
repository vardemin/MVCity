package com.vardemin.vcity.data.remote;

import com.github.nkzawa.emitter.Emitter;

/**
 * Created by SimUser on 18.07.2017.
 */

public interface IRemoteDataRepository {
    void connect();
    void disconnect();
    boolean isConnected();
    void listenOn(String eventName, Emitter.Listener listener);
    void offListenOn(String eventName, Emitter.Listener listener);
}
