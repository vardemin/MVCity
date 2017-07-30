package com.vardemin.vcity.data.remote;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by SimUser on 18.07.2017.
 */

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


}
