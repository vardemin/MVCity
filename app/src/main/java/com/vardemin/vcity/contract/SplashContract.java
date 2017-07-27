package com.vardemin.vcity.contract;

/**
 * Created by SimUser on 24.07.2017.
 */

public interface SplashContract {
    interface View extends MVPContract.View {
        void navigateLoginScreen();
        void navigateMainScreen();
    }

    interface Presenter extends MVPContract.Presenter {

    }
}
