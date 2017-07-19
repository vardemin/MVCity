package com.vardemin.vcity.contract;

public interface LoginContract {

    interface View extends MVPContract.View {
        void login();
        void showLoginError(String err);
    }

    interface Presenter extends MVPContract.Presenter {
        void requestLogin(String code);
    }
}
