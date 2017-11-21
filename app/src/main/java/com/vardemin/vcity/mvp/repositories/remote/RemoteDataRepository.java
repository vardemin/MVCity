package com.vardemin.vcity.mvp.repositories.remote;


import com.vardemin.vcity.data.models.Photo;
import com.vardemin.vcity.data.models.scheme.PhotoScheme;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.socket.client.Ack;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;


public class RemoteDataRepository implements IRemoteDataRepository {
    private final Socket socket;
    private final Retrofit retrofit;

    public RemoteDataRepository(Socket socket, Retrofit retrofit) {
        this.socket = socket;
        this.retrofit = retrofit;
    }


    @Override
    public void connect() {
        socket.connect();
    }

    @Override
    public void disconnect() {
        socket.disconnect();
    }

    @Override
    public boolean isConnected() {
        if(socket!=null)
            return socket.connected();
        else return false;
    }

    @Override
    public void listenOn(String eventName, Emitter.Listener listener) {
        socket.on(eventName, listener);
    }

    @Override
    public void offListenOn(String eventName, Emitter.Listener listener) {
        socket.off(eventName, listener);
    }

    @Override
    public void emit(String eventName, Object data, Ack ack) {
        socket.emit(eventName, data, ack);
    }

    @Override
    public void emit(SocketResultListener listener, String eventName, Object... data) {
        socket.emit(eventName, data, new Ack() {
            @Override
            public void call(Object... args) {
                if(args.length>1)
                    listener.onResult((JSONObject) args[1]);
                else try {
                    listener.onError(((JSONObject)args[0]).getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Observable<PhotoScheme> uploadImage(MultipartBody.Part file, String token) {
        return retrofit.create(RestService.class).uploadImage(file, token);
    }


}
