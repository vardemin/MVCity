package com.vardemin.vcity.presenter;

import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.MainContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (MainContract.View) view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public boolean isViewAlive() {
        return view!=null;
    }
}
