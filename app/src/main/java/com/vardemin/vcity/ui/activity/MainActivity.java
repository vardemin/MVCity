package com.vardemin.vcity.ui.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ncapdevi.fragnav.FragNavController;
import com.tapadoo.alerter.Alerter;
import com.vardemin.vcity.R;
import com.vardemin.vcity.eventbus.NavigationEvent;
import com.vardemin.vcity.mvp.views.BaseView;
import com.vardemin.vcity.ui.fragment.LifeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by xavie on 30.07.2017.
 */

public class MainActivity extends MvpAppCompatActivity implements FragNavController.RootFragmentListener, BaseView {

    @BindView(R.id.main_bottom_bar)
    TabLayout bottomBar;

    @BindView(R.id.top_bar_btn_back)
    View navBack;

    FragNavController fragNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragNavController.Builder builder = FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.main_container);
        builder.rootFragmentListener(this, 5);
        fragNav = builder.build();
        bottomBar.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0) {
                    fragNav.switchTab(0);
                }
                onNavigation();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragNav != null) {
            fragNav.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Fragment getRootFragment(int i) {
        switch (i) {
            case 0: return LifeFragment.getInstance();
        }
        return null;
    }

    @Override
    public void showError(String error) {
        Alerter.create(this).setTitle(R.string.error).setText(error).show();
        //Snackbar.make(bottomBar,error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean state) {

    }

    void onNavigation() {
        navBack.setVisibility((fragNav.getCurrentStackIndex()>0)?View.VISIBLE: View.GONE);
    }

    @OnClick(R.id.top_bar_btn_back)
    void onBack() {
        fragNav.popFragment();
        onNavigation();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(NavigationEvent event) {
        Log.d("NAVIGATION EVENT",event.getNextRoute().name());
        switch (event.getNextRoute()) {

        }
    }


}
