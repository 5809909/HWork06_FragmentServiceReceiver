package com.github.a5809909.hwork06_fragmentservicereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.a5809909.hwork06_fragmentservicereceiver.fragments.FragmentSignIn;
import com.github.a5809909.hwork06_fragmentservicereceiver.fragments.FragmentSignUp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentSignIn mFragmentSignIn;
    private FragmentSignUp mFragmentSignUp;
    private Fragment presentfragment;
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentSignIn = new FragmentSignIn();
        mFragmentSignUp = new FragmentSignUp();
        presentfragment = mFragmentSignUp;

        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_sign_up).setOnClickListener(this);
        final TextView textView =(TextView) findViewById(R.id.text_result);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String text = intent.getStringExtra(FragmentSignUp.KEY_FROM_FRAGMENT_SIGN_UP);
                text += ", you are successfully registred. Congratulations! \n Please Sign In";
                textView.setText(text);
                showFragment(mFragmentSignIn);

            }
        };

        IntentFilter filter = new IntentFilter(FragmentSignUp.BROADCAST_ACTION);
        this.registerReceiver(broadcastReceiver, filter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
               case R.id.btn_sign_in:
                showFragment(mFragmentSignIn);
                break;
            case R.id.btn_sign_up:
                showFragment(mFragmentSignUp);
                break;
            default:
        }


    }


    private void showFragment(final Fragment fragment) {
        final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        fragmentManager.beginTransaction()
                //   .replace(R.id.fragment_container, fragment)
                .hide(presentfragment)
                .show(fragment)
                .commit();
        presentfragment = fragment;
    }

    public void ControlServiceActivity() {
        Intent intent = new Intent(this, ControlService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(this.broadcastReceiver);
    }

}