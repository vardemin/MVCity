package com.vardemin.vcity.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.tapadoo.alerter.Alerter;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.mvp.views.EventView;

import java.util.List;

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

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_event, container, false);
        ButterKnife.bind(this, view);
        getDialog().setTitle(R.string.add_event);
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }

        return view;
    }

    @Override
    public void showError(String error) {
        Alerter.create(getActivity()).setTitle(R.string.error).setText(error).show();
    }

    @Override
    public void showLoading(boolean state) {
        progressBar.setVisibility(state?View.VISIBLE:View.GONE);
    }

    @Override
    public void onEventList(List<EventScheme> schemes) {

    }
}
