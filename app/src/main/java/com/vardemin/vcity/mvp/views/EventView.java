package com.vardemin.vcity.mvp.views;


import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.vardemin.vcity.data.models.scheme.EventScheme;

import java.util.List;

/**
 * Created by xavie on 08.08.2017.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface EventView extends BaseView {
    void showLoading(boolean state);
    void onEventList(List<EventScheme> schemes);
}
