package com.vardemin.vcity.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;

import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.other.RegistrationData;
import com.vardemin.vcity.mvp.presenters.RegistrationPresenter;
import com.vardemin.vcity.mvp.views.RegistrationView;
import com.vardemin.vcity.util.DateUtil;

import java.text.ParseException;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xavie on 08.08.2017.
 */

public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView, DatePickerDialog.OnDateSetListener{

    @InjectPresenter(type = PresenterType.WEAK, tag = RegistrationPresenter.TAG)
    RegistrationPresenter presenter;

    @BindView(R.id.register_email)
    EditText email;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.register_name)
    EditText name;
    @BindView(R.id.register_birth)
    TextView birth;
    @BindView(R.id.register_radio_male)
    RadioButton radioMale;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);
        //App.getLoginComponent().inject(this);
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        email.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                presenter.onEmail(textView.getText().toString());
                return true;
            }
            return false;
        });
        password.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                presenter.onPassword(textView.getText().toString());
                return true;
            }
            return false;
        });
        name.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                presenter.onName(textView.getText().toString());
                return true;
            }
            return false;
        });
        radioMale.setOnClickListener(view1 -> presenter.onSex(radioMale.isChecked()));

    }

    public static RegistrationFragment getInstance() {
        return new RegistrationFragment();
    }


    @Override
    public void showRegistrationError(String err) {
        getActivity().runOnUiThread(() -> {
            Snackbar.make(birth,err,Snackbar.LENGTH_SHORT).show();
        });
    }

    @Override
    public void setEmailField(String email) {
        getActivity().runOnUiThread(() -> {
            this.email.setText(email);
        });
    }

    @Override
    public void setPasswordField(String password) {
        getActivity().runOnUiThread(() -> {
            this.password.setText(password);
        });
    }

    @Override
    public void setNameField(String name) {
        getActivity().runOnUiThread(() -> {
            this.name.setText(name);
        });
    }

    @Override
    public void setBirthField(String birth) {
        getActivity().runOnUiThread(() -> {
            this.birth.setText(birth);
        });
    }

    @Override
    public void setSexField(Boolean male) {
        getActivity().runOnUiThread(() -> {
            this.radioMale.setChecked(male);
        });
    }


    @Override
    public void showError(String err) {
        getActivity().runOnUiThread(() -> {
            Snackbar.make(birth,err,Snackbar.LENGTH_SHORT).show();
        });

    }

    @OnClick(R.id.register_pick_date_btn)
    void pickDate() {
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(getActivity(),this, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        StringBuilder sb = new StringBuilder();
        sb.append(day).append('.').append(month).append('.').append(year);
        //birth.setText(sb);
        presenter.onBirth(sb.toString());
    }

    @OnClick(R.id.register_btn)
    void onRegisterBtn() {
        try {
            RegistrationData data = new RegistrationData(email.getText().toString(),name.getText().toString(),
                    password.getText().toString(), DateUtil.getDateFromStr(birth.getText().toString()), radioMale.isChecked());
            presenter.register(data);
        } catch (ParseException e) {
            e.printStackTrace();
            showError(e.getLocalizedMessage());
        }

    }
}
