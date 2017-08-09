package com.vardemin.vcity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.mvp.presenters.RegistrationPresenter;
import com.vardemin.vcity.mvp.views.RegistrationView;

import javax.inject.Inject;

/**
 * Created by xavie on 08.08.2017.
 */

public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView{

    @Inject
    RegistrationPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        App.getLoginComponent().inject(this);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public  void onStop() {
        super.onStop();
    }

    @Override
    public void showRegistrationError(String err) {

    }

    @Override
    public void navigateMainScreen() {

    }


    @Override
    public void showError(String err) {

    }
}
