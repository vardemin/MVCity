package com.vardemin.vcity.data.remote;

import com.github.nkzawa.socketio.client.Socket;

/**
 * Created by SimUser on 18.07.2017.
 */

public class RemoteDataRepository implements IRemoteDataRepository {
    private final Socket socket;

    public RemoteDataRepository(Socket socket) {
        this.socket = socket;
    }
}
