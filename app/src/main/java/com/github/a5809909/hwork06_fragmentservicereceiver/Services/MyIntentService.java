package com.github.a5809909.hwork06_fragmentservicereceiver.Services;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import com.github.a5809909.hwork06_fragmentservicereceiver.constants.*;

public class MyIntentService extends IntentService {

    MyBroadcastReceiver mMyBroadcastReceiver;

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(), "Service START", Toast.LENGTH_SHORT).show();
        mMyBroadcastReceiver = new MyBroadcastReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACT_SEND_MESSAGE);
        registerReceiver(mMyBroadcastReceiver, intentFilter);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "Service STOP", Toast.LENGTH_SHORT).show();
        unregisterReceiver(mMyBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(100);
                Intent backIntent = new Intent();
                backIntent.setAction(Constants.ACT_PROGRESSBAR);
                backIntent.putExtra(Constants.PROGRESS, i);
                sendBroadcast(backIntent);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Constants.ACT_SEND_MESSAGE)) {
                String message = intent.getStringExtra(Constants.MESSAGE_SEND);

                Intent intentResponse = new Intent();
                intentResponse.setAction(Constants.ACT_RESPONSE_MSG);
                intentResponse.putExtra(Constants.RESPONSE_MESSAGE, message);
                sendBroadcast(intentResponse);
            }
        }
    }
}