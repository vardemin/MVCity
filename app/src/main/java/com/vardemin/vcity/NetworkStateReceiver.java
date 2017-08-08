package com.vardemin.vcity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.vardemin.vcity.eventbus.NetworkStateEvent;
import com.vardemin.vcity.util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xavie on 08.08.2017.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new NetworkStateEvent(NetworkUtil.isNetworkConnected(context)));
        Toast.makeText(context,"Network state changed",Toast.LENGTH_SHORT).show();
    }
}
