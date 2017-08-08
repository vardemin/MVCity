package com.vardemin.vcity.mvp.views;

/**
 * Created by xavie on 08.08.2017.
 */

public interface LoginView extends BaseView {
    void showLoginError(String error);
    void navigateMainScreen();
}
