package com.vardemin.vcity.contract;

import com.vardemin.vcity.presenter.BasePresenter;

public class LoginContract {

    interface View {
        void setLoading(boolean state);
        void login();
        void onAuthorized();
        void showError(String errorMessage);
    }

    interface Presenter extends BasePresenter<View> {
        void login(String code);
    }
}
