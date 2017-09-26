package com.vardemin.vcity.mvp.repositories.remote;


import com.vardemin.vcity.data.models.Photo;

import io.reactivex.Observable;
import io.socket.client.Ack;
import io.socket.emitter.Emitter;
import okhttp3.MultipartBody;


public interface IRemoteDataRepository {

    void connect();
    void disconnect();
    boolean isConnected();
    void listenOn(String eventName, Emitter.Listener listener);
    void offListenOn(String eventName, Emitter.Listener listener);
    void emit(String eventName, Object data, Ack ack);
    void emit(SocketResultListener listener, String eventName, Object... data);

    Observable<Photo> uploadImage(MultipartBody.Part file, String token);
}
