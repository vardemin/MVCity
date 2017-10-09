package com.vardemin.vcity.mvp.views;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by xavie on 08.08.2017.
 */

public interface SplashView extends BaseView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setAuthorized(boolean isAuthorized);
}
