package com.vardemin.vcity.mvp.repositories.remote;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;

/**
 * Created by SimUser on 18.07.2017.
 */

public interface IRemoteDataRepository {
    void connect();
    void disconnect();
    boolean isConnected();
    void listenOn(String eventName, Emitter.Listener listener);
    void offListenOn(String eventName, Emitter.Listener listener);
    void emit(String eventName, Object data, Ack ack);
}
