package com.vardemin.vcity.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.vardemin.vcity.R;
import com.vardemin.vcity.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by vard on 30.11.17.
 */

public class ActivityServer extends AppCompatActivity {
    @BindView(R.id.edit_server)
    EditText editServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        ButterKnife.bind(this);
        editServer.setText(Constants.SERVER_URL);
    }

    @OnClick(R.id.btn_enter)
    void onEnter() {
        Constants.SERVER_URL = editServer.getText().toString();
        startActivity(new Intent(this, SplashActivity.class));
    }
}
