package com.vardemin.vcity.presenter;

import com.vardemin.vcity.contract.InstitutionContract;
import com.vardemin.vcity.contract.MVPContract;

/**
 * Created by xavie on 19.07.2017.
 */

public class InstitutionPresenter implements InstitutionContract.Presenter {
    private InstitutionContract.View view;

    @Override
    public MVPContract.View getView() {
        return view;
    }

    @Override
    public void attachView(MVPContract.View view) {
        this.view = (InstitutionContract.View) view;
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
