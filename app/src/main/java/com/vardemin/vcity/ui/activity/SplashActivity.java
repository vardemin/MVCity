package com.vardemin.vcity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        App.getSplashComponent().inject(this);
    }

    @Override
    public void showLoading(boolean state) {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void navigateLoginScreen() {
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void navigateMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
