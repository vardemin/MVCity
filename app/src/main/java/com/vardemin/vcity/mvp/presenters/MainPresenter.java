package com.vardemin.vcity.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vardemin.vcity.App;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;
import com.vardemin.vcity.mvp.views.BaseView;


import javax.inject.Inject;

/**
 * Created by xavie on 19.07.2017.
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<BaseView> {
    @Inject
    ILocalDataRepository localDataRepository;
    @Inject
    IRemoteDataRepository remoteDataRepository;

    public MainPresenter() {
        App.getApplicationComponent().inject(this);
    }
}
