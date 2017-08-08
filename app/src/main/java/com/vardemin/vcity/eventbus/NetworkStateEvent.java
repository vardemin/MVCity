package com.vardemin.vcity.eventbus;

/**
 * Created by xavie on 08.08.2017.
 */

public class NetworkStateEvent {
    private boolean isConnected = false;

    public NetworkStateEvent(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isConnected() {
        return isConnected;
    }

}
