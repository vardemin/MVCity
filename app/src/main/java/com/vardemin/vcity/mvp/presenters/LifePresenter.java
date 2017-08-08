package com.vardemin.vcity.mvp.presenters;

import com.vardemin.vcity.contract.LifeContract;
import com.vardemin.vcity.contract.MVPContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class LifePresenter implements LifeContract.Presenter {

    private LifeContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (LifeContract.View) view;
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
