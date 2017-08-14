package com.vardemin.vcity.mvp.views;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by xavie on 08.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface RegistrationView extends BaseView {
    void showRegistrationError(String err);
    void setEmailField(String email);
    void setPasswordField(String password);
    void setNameField(String name);
    void setBirthField(String birth);
    void setSexField(Boolean male);
}
