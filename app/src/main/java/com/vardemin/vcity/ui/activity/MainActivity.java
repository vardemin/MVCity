package com.vardemin.vcity.ui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ncapdevi.fragnav.FragNavController;
import com.vardemin.vcity.R;
import com.vardemin.vcity.mvp.views.BaseView;
import com.vardemin.vcity.ui.fragment.LifeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by xavie on 30.07.2017.
 */

public class MainActivity extends MvpAppCompatActivity implements FragNavController.RootFragmentListener, BaseView {

    @BindView(R.id.main_bottom_bar)
    TabLayout bottomBar;

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
    public Fragment getRootFragment(int i) {
        switch (i) {
            case 0: return LifeFragment.getInstance();
        }
        return null;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(bottomBar,error,Snackbar.LENGTH_SHORT).show();
    }
}
