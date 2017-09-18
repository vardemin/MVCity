package com.vardemin.vcity.mvp.repositories.remote;


import io.socket.client.Ack;
import io.socket.emitter.Emitter;


public interface IRemoteDataRepository {
    void connect();
    void disconnect();
    boolean isConnected();
    void listenOn(String eventName, Emitter.Listener listener);
    void offListenOn(String eventName, Emitter.Listener listener);
    void emit(String eventName, Object data, Ack ack);
    void emit(SocketResultListener listener, String eventName, Object... data);
}
