package com.vardemin.vcity.mvp.presenters;

import com.arellomobile.mvp.MvpPresenter;
import com.google.android.gms.maps.model.LatLng;
import com.vardemin.vcity.App;
import com.vardemin.vcity.contract.LifeContract;
import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.views.LifeView;

import javax.inject.Inject;

/**
 * Created by xavie on 19.07.2017.
 */

public class LifePresenter extends MvpPresenter<LifeView> {
    public static final String TAG = "life_presenter";

    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public LifePresenter() {
        App.getApplicationComponent().inject(this);
    }

    public void setLatLng(LatLng loc) {
        localDataRepository.setLatLng(loc);
    }

    public void cacheLatLng() {
        localDataRepository.cacheLatLng();
    }
}
