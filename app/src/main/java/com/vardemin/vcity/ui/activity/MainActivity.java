package com.vardemin.vcity.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ncapdevi.fragnav.FragNavController;
import com.vardemin.vcity.R;


/**
 * Created by xavie on 30.07.2017.
 */

public class MainActivity extends MvpAppCompatActivity implements FragNavController.RootFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.container);

    }

    @Override
    public Fragment getRootFragment(int i) {
        return null;
    }
}
