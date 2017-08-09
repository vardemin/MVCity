package com.vardemin.vcity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.contract.SplashContract;
import com.vardemin.vcity.mvp.presenters.SplashPresenter;
import com.vardemin.vcity.mvp.views.SplashView;

import javax.inject.Inject;

/**
 * Created by xavie on 17.07.2017.
 */

public class SplashActivity extends MvpAppCompatActivity implements SplashView{
    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }


    @Override
    public void setAuthorized(boolean isAuthorized) {

    }
}
