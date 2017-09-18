package com.vardemin.vcity.mvp.repositories.remote;


import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Ack;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class RemoteDataRepository implements IRemoteDataRepository {
    private final Socket socket;

    public RemoteDataRepository(Socket socket) {
        this.socket = socket;

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


}
