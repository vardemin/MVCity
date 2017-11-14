package com.vardemin.vcity.ui.dialog;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;

import com.vardemin.vcity.R;
import com.vardemin.vcity.data.models.scheme.EventScheme;
import com.vardemin.vcity.ui.adapter.EventCardsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vard on 14.11.17.
 */

public class EventListDialog extends BottomSheetDialogFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.switcher_name)
    TextSwitcher switcherName;
    @BindView(R.id.switcher_description)
    TextSwitcher switcherDescription;

    EventCardsAdapter adapter;

    private List<EventScheme> schemes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_life_events_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


}
