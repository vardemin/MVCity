package com.vardemin.vcity.mvp.presenters;

import com.vardemin.vcity.contract.MVPContract;
import com.vardemin.vcity.contract.MeetingsContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class MeetingsPresenter implements MeetingsContract.Presenter {

    private MeetingsContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (MeetingsContract.View) view;
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
