package com.vardemin.vcity.mvp.presenters;

import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.MeetContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class MeetPresenter implements MeetContract.Presenter {

    private MeetContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (MeetContract.View) view;
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
