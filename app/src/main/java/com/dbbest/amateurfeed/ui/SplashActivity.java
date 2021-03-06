package com.dbbest.amateurfeed.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dbbest.amateurfeed.App;
import com.dbbest.amateurfeed.model.AuthToken;
import com.dbbest.amateurfeed.model.CurrentUser;
import com.dbbest.amateurfeed.ui.util.UiActivityNavigation;
import com.dbbest.amateurfeed.utils.Utils;

/**
 * Created by antonina on 19.01.17.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int FADE_DELAY = 500;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                nextScreen();
            }
            return true;
        }
    });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        mHandler.sendEmptyMessageDelayed(1, FADE_DELAY);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(1);
    }


    private void nextScreen() {
        AuthToken authToken = App.getFactory().data().authToken();
        Intent intent;

        Log.i(Utils.TAG_LOG, authToken.toString());
        authToken.purge();

        if (authToken.isValid()) {

            intent = UiActivityNavigation.homeActivity(SplashActivity.this);

        } else {
            intent = UiActivityNavigation.startActivity(SplashActivity.this);

        }
        startActivity(intent);
        finish();
    }


}
