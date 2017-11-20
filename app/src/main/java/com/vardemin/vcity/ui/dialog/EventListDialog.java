package com.vardemin.vcity.ui.dialog;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.mvp.presenters.EventPresenter;
import com.vardemin.vcity.mvp.views.EventView;
import com.vardemin.vcity.ui.activity.MainActivity;
import com.vardemin.vcity.ui.adapter.EventCardsAdapter;
import com.vardemin.vcity.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vard on 14.11.17.
 */

public class EventListDialog extends BottomSheetDialogFragment implements EventView{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.switcher_name)
    TextSwitcher switcherName;
    @BindView(R.id.switcher_description)
    TextSwitcher switcherDescription;

    @BindView(R.id.btn_expand)
    TextView header;

    EventCardsAdapter adapter;

    private List<EventScheme> schemes;
    private CardSliderLayoutManager layoutManager;
    private int currentPosition;

    private boolean mIsStateSaved;
    private MvpDelegate<? extends EventListDialog> mMvpDelegate;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_life_events_list, container, false);
        ButterKnife.bind(this, view);
        if (schemes!=null)
            header.setText(String.format(getString(R.string.found_pattern),schemes.size(),getString(R.string.plural_event)));
        return view;
    }


    @Override
    public void showLoading(boolean state) {

    }


    @Override
    public void onEventList(List<EventScheme> schemes) {
        this.schemes = schemes;
        adapter = new EventCardsAdapter(schemes, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void showError(String error) {
        Log.d("LOG", error);
    }

    private void onActiveCardChange() {
        final int pos = layoutManager.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }
    private void onActiveCardChange(int pos) {
        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        currentPosition = pos;
    }



    private class OnCardClickListener implements View.OnClickListener {
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
                /*final Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, pics[activeCardPosition % pics.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(MainActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }*/
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }
}
