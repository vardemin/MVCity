package com.vardemin.vcity.contract;

public interface MainContract {

    interface View extends MVPContract.View {
        void onAuthorized();
    }

    interface Presenter extends MVPContract.Presenter {

    }
}
