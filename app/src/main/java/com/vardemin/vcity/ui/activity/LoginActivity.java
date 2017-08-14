package com.vardemin.vcity.ui.activity;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.contract.LoginContract;
import com.vardemin.vcity.eventbus.NavigationEvent;
import com.vardemin.vcity.mvp.presenters.LoginPresenter;
import com.vardemin.vcity.ui.fragment.LoginFragment;
import com.vardemin.vcity.ui.fragment.RegistrationFragment;
import com.vardemin.vcity.util.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{


    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState!=null) {
            String tag = savedInstanceState.getString("TAG");
            if (tag!=null)
                switch (tag) {
                    case Constants.LOGIN_SUBSCREEN:
                        replaceFragment(LoginFragment.getInstance(),tag,false);
                        break;
                    case Constants.REGISTRATION_SUBSCREEN:
                        replaceFragment(RegistrationFragment.getInstance(),tag,false);
                        break;
                }
        }
        else
            replaceFragment(LoginFragment.getInstance(),Constants.LOGIN_SUBSCREEN, false);
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
    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragmentManager.getBackStackEntryCount()>0)
            outState.putString("TAG", fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount()-1).getName());
        else outState.putString("TAG", Constants.LOGIN_SUBSCREEN);
    }

    @Subscribe
    void onEvent(NavigationEvent event) {
        Fragment fragment;
        switch (event.getNextRoute()) {
            case LOGIN_SUBSCREEN:
                fragment = fragmentManager.findFragmentByTag(Constants.LOGIN_SUBSCREEN);
                replaceFragment(fragment!=null?fragment:LoginFragment.getInstance(),Constants.LOGIN_SUBSCREEN, true);
                break;
            case REGISTRATION_SUBSCREEN:
                fragment = fragmentManager.findFragmentByTag(Constants.REGISTRATION_SUBSCREEN);
                replaceFragment(fragment!=null?fragment:RegistrationFragment.getInstance(),Constants.REGISTRATION_SUBSCREEN, true);
                break;
            case MAIN_SCREEN:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    private void replaceFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.login_frame, fragment , tag);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

}

