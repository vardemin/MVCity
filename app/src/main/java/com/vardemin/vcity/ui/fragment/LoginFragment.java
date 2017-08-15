package com.vardemin.vcity.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
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

    @InjectPresenter(type = PresenterType.WEAK, tag = LoginPresenter.TAG)
    LoginPresenter presenter;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.login_layout)
    TextInputLayout login_layout;
    @BindView(R.id.login_password)
    EditText password_input;
    @BindView(R.id.login_email)
    EditText login_input;
    @BindView(R.id.login_progress)
    ProgressBar progressBar;

    public static LoginFragment getInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        //App.getLoginComponent().inject(this);
        //ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            login_input.setText(savedInstanceState.getString("email"));
            password_input.setText(savedInstanceState.getString("password"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("email",login_input.getText().toString());
        outState.putString("password",password_input.getText().toString());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
/*        login_input.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                presenter.onEmail(textView.getText().toString());
                return true;
            }
            return false;
        });
        password_input.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                presenter.onPassword(textView.getText().toString());
                return true;
            }
            return false;
        });*/
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

 /*   @Override
    public void setEmailField(String email) {
        getActivity().runOnUiThread(() -> {
            this.login_input.setText(email);
        });
    }

    @Override
    public void setPasswordField(String password) {
        getActivity().runOnUiThread(() -> {
            this.password_input.setText(password);
        });
    }*/

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

    @OnClick(R.id.login_to_register_btn)
    void onRegister() {
        presenter.onRegistration();
    }
}
