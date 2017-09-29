package com.vardemin.vcity.ui.dialog;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.vardemin.vcity.R;
import com.vardemin.vcity.mvp.views.EventView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xavie on 28.09.2017.
 */

public class AddEventDialog extends DialogFragment implements EventView {

    private int animDuration;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_description)
    EditText editDescr;
    @BindView(R.id.btn_date)
    Button btnDate;
    @BindView(R.id.check_location)
    CheckBox checkLoco;
    @BindView(R.id.progress)
    ProgressBar progressBar;



    public static AddEventDialog newInstance() {
        return new AddEventDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_event, container, false);
        ButterKnife.bind(this, view);
        getDialog().setTitle(R.string.add_event);

        return view;
    }

    @Override
    public void showError(String error) {
        Snackbar.make(editName,error,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean state) {
        progressBar.setVisibility(state?View.VISIBLE:View.GONE);
    }
}
