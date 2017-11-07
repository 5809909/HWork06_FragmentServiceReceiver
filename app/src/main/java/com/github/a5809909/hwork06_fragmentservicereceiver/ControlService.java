package com.github.a5809909.hwork06_fragmentservicereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.github.a5809909.hwork06_fragmentservicereceiver.constants.*;

import com.github.a5809909.hwork06_fragmentservicereceiver.Services.MyIntentService;

public class ControlService extends AppCompatActivity {
    Button btnStartService;
    Button btnSendMessage;
    EditText editTextMessage;
    TextView textViewMsgReceived;
    ProgressBar progressbar;

    MyReceiver myReceiver;
    Intent myIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_service);

        btnStartService = (Button) findViewById(R.id.btn_start);
        btnSendMessage = (Button) findViewById(R.id.btn_send);
        editTextMessage = (EditText) findViewById(R.id.msg_to_send);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        textViewMsgReceived = (TextView) findViewById(R.id.text_view_response);

        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIntent = new Intent(ControlService.this, MyIntentService.class);
                startService(myIntent);

            }
        });

        
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgToSend = editTextMessage.getText().toString();

                Intent intent = new Intent();
                intent.setAction(Constants.ACT_SEND_MESSAGE);
                intent.putExtra(Constants.MESSAGE_SEND, msgToSend);
                sendBroadcast(intent);
            }
        });
    }


    @Override
    protected void onStart(){
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACT_PROGRESSBAR);
        intentFilter.addAction(Constants.ACT_RESPONSE_MSG);
        registerReceiver(myReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop(){
        unregisterReceiver(myReceiver);
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(Constants.ACT_PROGRESSBAR)){
                int int_from_service = intent.getIntExtra(Constants.PROGRESS,0);
                progressbar.setProgress(int_from_service);
            }else  if(action.equals(Constants.ACT_RESPONSE_MSG)){
                String responseMessage = intent.getStringExtra(Constants.RESPONSE_MESSAGE);
                textViewMsgReceived.setText(responseMessage);
            }
        }
    }

}
