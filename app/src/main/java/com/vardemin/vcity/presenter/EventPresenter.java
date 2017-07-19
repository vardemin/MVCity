package com.vardemin.vcity.presenter;

import com.vardemin.vcity.contract.EventContract;
import com.vardemin.vcity.contract.MVPContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class EventPresenter implements EventContract.Presenter {
    private EventContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (EventContract.View) view;
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
