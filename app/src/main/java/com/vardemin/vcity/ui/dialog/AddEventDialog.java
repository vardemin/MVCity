package com.vardemin.vcity.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;
import com.tapadoo.alerter.Alerter;
import com.vardemin.vcity.App;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.eventbus.ChoosePhotoEvent;
import com.vardemin.vcity.mvp.presenters.EventPresenter;
import com.vardemin.vcity.mvp.views.EventView;
import com.vardemin.vcity.ui.adapter.UriPhotosAdapter;
import com.vardemin.vcity.util.Constants;
import com.vardemin.vcity.util.DateUtil;
import com.vardemin.vcity.util.MvpBottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xavie on 28.09.2017.
 */

public class AddEventDialog extends MvpBottomSheetDialogFragment implements EventView, UriPhotosAdapter.UriAdapterListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_description)
    EditText editDescr;
    @BindView(R.id.btn_date)
    Button btnDate;
    @BindView(R.id.btn_time)
    Button btnTime;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @InjectPresenter(type = PresenterType.WEAK,tag = EventPresenter.ADD_EVENT_TAG)
    EventPresenter presenter;

    private List<Uri> photos;
    private UriPhotosAdapter adapter;

    private CardSliderLayoutManager layoutManager;
    private int currentPosition;

    private Calendar calendar = Calendar.getInstance();

    public static AddEventDialog newInstance() {
        return new AddEventDialog();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_new_event, container, false);
        photos = new ArrayList<>();
        photos.add(null);
        ButterKnife.bind(this, view);
        adapter = new UriPhotosAdapter(photos, App.getApplicationComponent().context(), this);
        recyclerView.setLayoutManager(new CardSliderLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = (CardSliderLayoutManager) recyclerView.getLayoutManager();

        btnDate.setText(DateUtil.getStrFromDate(calendar.getTime()));
        btnTime.setText(DateUtil.getTimeStrFromDate(calendar.getTime()));

        new CardSnapHelper().attachToRecyclerView(recyclerView);
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
    public void onEventPosted(EventScheme event) {
        dismiss();
    }

    @Override
    public void onEventList(List<EventScheme> schemes) {

    }

    @Override
    public void onStart() {
        super.onStart();
        /*Dialog dialog = getDialog();

        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());

        });*/
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onChoosePhotoEvent(ChoosePhotoEvent event) {
        Log.d("LOG", "EVENT");
        photos.add(photos.size()-1, event.getPhoto());
        adapter.notifyItemInserted(photos.size()-2);
    }

    @OnClick(R.id.btn_date)
    void onDate() {
        new DatePickerDialog(getContext(),this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    @OnClick(R.id.btn_time)
    void onTime() {
        new TimePickerDialog(getContext(),this,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true).show();
    }

    @OnClick(R.id.btn_post)
    void onPost() {
        presenter.postEvent(editName.getText().toString(),editDescr.getText().toString(),calendar.getTime(),photos);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        btnDate.setText(DateUtil.getStrFromDate(calendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        btnTime.setText(DateUtil.getTimeStrFromDate(calendar.getTime()));
    }

    @Override
    public void onAdd(int position) {
        final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

        if (lm.isSmoothScrolling()) {
            return;
        }

        final int activeCardPosition = lm.getActiveCardPosition();
        if (activeCardPosition == RecyclerView.NO_POSITION) {
            return;
        }

        if (position == activeCardPosition) {
            Log.d("LOG CLICK BTN_ADD", "CLICKED");
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            getActivity().startActivityForResult(Intent.createChooser(intent,
                    "Select Picture"), Constants.CODE_CHOOSE_PHOTO);
        }
        else if (position > activeCardPosition) {
            recyclerView.smoothScrollToPosition(position);
        }

    }

    @Override
    public void onClose(int position) {
        final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

        if (lm.isSmoothScrolling()) {
            return;
        }

        final int activeCardPosition = lm.getActiveCardPosition();
        if (activeCardPosition == RecyclerView.NO_POSITION) {
            return;
        }

        if (position == activeCardPosition) {
            photos.remove(position);
            adapter.notifyItemRemoved(position);
        }
        else if (position > activeCardPosition) {
            recyclerView.smoothScrollToPosition(position);
        }
    }
}
