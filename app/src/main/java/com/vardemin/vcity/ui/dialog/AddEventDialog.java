package com.vardemin.vcity.ui.dialog;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xavie on 28.09.2017.
 */

public class AddEventDialog extends BottomSheetDialogFragment implements EventView, View.OnClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.edit_description)
    EditText editDescr;
    @BindView(R.id.btn_date)
    Button btnDate;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @InjectPresenter(type = PresenterType.WEAK,tag = EventPresenter.ADD_EVENT_TAG)
    EventPresenter presenter;

    private List<Uri> photos;
    private UriPhotosAdapter adapter;

    private CardSliderLayoutManager layoutManager;
    private int currentPosition;

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
        recyclerView.setHasFixedSize(true);

        layoutManager = (CardSliderLayoutManager) recyclerView.getLayoutManager();

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
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onChoosePhotoEvent(ChoosePhotoEvent event) {
        photos.add(photos.size()-1, event.getPhoto());
        adapter.notifyItemInserted(photos.size()-1);
    }


    @Override
    public void onClick(View view) {

        final CardSliderLayoutManager lm =  (CardSliderLayoutManager) recyclerView.getLayoutManager();

        if (lm.isSmoothScrolling()) {
            return;
        }

        final int activeCardPosition = lm.getActiveCardPosition();
        if (activeCardPosition == RecyclerView.NO_POSITION) {
            return;
        }

        final int clickedPosition = recyclerView.getChildAdapterPosition(view);
        if (clickedPosition == activeCardPosition) {
            int id = view.getId();
            switch (id) {
                case R.id.btn_add:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), Constants.CODE_CHOOSE_PHOTO);
                    break;
                case R.id.btn_close:
                    photos.remove(clickedPosition);
                    adapter.notifyItemRemoved(clickedPosition);
                    break;
            }
        }
        else if (clickedPosition > activeCardPosition) {
            recyclerView.smoothScrollToPosition(clickedPosition);
        }
    }

    
}
