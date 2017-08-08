package com.vardemin.vcity.contract;

/**
 * Created by xavie on 08.08.2017.
 */

public interface RegistrationContract {
    interface View extends MVPContract.View {
        void showRegistrationError(String err);
        void navigateLoginScreen();
    }

    interface Presenter extends MVPContract.Presenter {
        void register(String name, String password);
    }
}
