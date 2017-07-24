package com.vardemin.vcity.presenter;

import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.SplashContract;

/**
 * Created by SimUser on 24.07.2017.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (SplashContract.View) view;
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
