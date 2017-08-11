package com.vardemin.vcity.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.mvp.presenters.LoginPresenter;
import com.vardemin.vcity.mvp.views.LoginView;
import com.vardemin.vcity.ui.activity.MainActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xavie on 08.08.2017.
 */

public class LoginFragment extends MvpAppCompatFragment implements LoginView {

    @InjectPresenter
    LoginPresenter presenter;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.login_layout)
    TextInputLayout login_layout;
    @BindView(R.id.password)
    EditText password_input;
    @BindView(R.id.email)
    EditText login_input;
    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        //App.getLoginComponent().inject(this);
        //ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
    }

    @Override
    public void showLoginError(String error) {
        getActivity().runOnUiThread(() -> {
            if (error.contains("login"))
                login_layout.setError(error);
            else
                password_layout.setError(error);
        });
    }

    @Override
    public void navigateMainScreen() {
        startActivity(new Intent(getActivity(),MainActivity.class));
    }

    @Override
    public void showError(String error) {
        Snackbar.make(login_layout,error,Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.email_sign_in_button)
    public void onSubmit() {
        if (!login_input.getText().toString().equals("")&&!password_input.getText().toString().equals(""))
        {
            password_layout.setError("");
            presenter.login(login_input.getText().toString(),password_input.getText().toString());
        }
        else {
            password_layout.setError(getString(R.string.empty_field));
        }
    }
}
