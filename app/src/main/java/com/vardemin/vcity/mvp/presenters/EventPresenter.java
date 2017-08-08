package com.vardemin.vcity.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.vardemin.vcity.contract.EventContract;
import com.vardemin.vcity.contract.MVPContract;

/**
 * Created by xavie on 19.07.2017.
 */
@InjectViewState
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
