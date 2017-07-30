package com.vardemin.vcity.contract;

public interface LoginContract {

    interface View extends MVPContract.View {
        void showLoginError(String err);
        void navigateMainScreen();
    }

    interface Presenter extends MVPContract.Presenter {
        void login(String name, String password);
    }
}
