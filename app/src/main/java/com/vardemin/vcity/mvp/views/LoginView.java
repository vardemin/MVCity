package com.vardemin.vcity.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by xavie on 08.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends BaseView {
    void showLoginError(String error);
    void setEmailField(String email);
    void setPasswordField(String password);
}
