package com.vardemin.vcity.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.contract.LoginContract;
import com.vardemin.vcity.presenter.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.password_layout)
    TextInputLayout password_layout;
    @BindView(R.id.password)
    EditText password_input;
    @BindView(R.id.login)
    EditText login_input;
    @BindView(R.id.login_progress)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        ((App)getApplication()).getLoginComponent().inject(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStop(){
        presenter.detachView();
        super.onStop();
    }


    @Override
    public void showLoginError(String err) {
        password_layout.setError(err);
    }

    @Override
    public void navigateMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showLoading(boolean state) {
        progressBar.setVisibility(state?View.VISIBLE:View.GONE);
    }

    @Override
    public void showError(String err) {
        Snackbar.make(password_layout, err, BaseTransientBottomBar.LENGTH_SHORT);
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

