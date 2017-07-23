package com.vardemin.vcity.data.local;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by SimUser on 18.07.2017.
 */

public class LocalDataRepository implements ILocalDataRepository {
    private Realm realm;
    private Context context;

    public LocalDataRepository(Context context, Realm realm) {
        this.context = context;
        this.realm = realm;
    }
}
