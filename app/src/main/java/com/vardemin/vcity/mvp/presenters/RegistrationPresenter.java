package com.vardemin.vcity.mvp.presenters;

import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.RegistrationContract;
import com.vardemin.vcity.mvp.repositories.local.ILocalDataRepository;
import com.vardemin.vcity.mvp.repositories.remote.IRemoteDataRepository;

/**
 * Created by xavie on 08.08.2017.
 */

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;
    private ILocalDataRepository localDataRepository;
    private IRemoteDataRepository remoteDataRepository;

    public RegistrationPresenter(ILocalDataRepository localDataRepository, IRemoteDataRepository remoteDataRepository) {
        this.localDataRepository = localDataRepository;
        this.remoteDataRepository = remoteDataRepository;
    }


    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (RegistrationContract.View) view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAlive() {
        return view!=null;
    }

    @Override
    public void register(String name, String password) {

    }
}
