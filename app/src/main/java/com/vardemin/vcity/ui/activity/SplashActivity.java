package com.vardemin.vcity.ui.activity;

import android.support.v7.app.AppCompatActivity;

import com.vardemin.vcity.contract.SplashContract;
import com.vardemin.vcity.presenter.SplashPresenter;

import javax.inject.Inject;

/**
 * Created by xavie on 17.07.2017.
 */

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    @Inject
    SplashPresenter presenter;

    @Override
    public void showLoading(boolean state) {

    }

    @Override
    public void showError(String err) {

    }
}
