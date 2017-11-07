package com.github.a5809909.hwork06_fragmentservicereceiver.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.a5809909.hwork06_fragmentservicereceiver.R;

public class FragmentSignUp extends Fragment {
    public static final String BROADCAST_ACTION = "com.github.a5809909.hwork06_fragmentservicereceiver.fragments.FragmentSignUp";
    public static final String KEY_FROM_FRAGMENT_SIGN_UP= "text";
    private EditText editTextLoginSignUp;
    private Button buttonEnterignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up_layout, container, false);

        editTextLoginSignUp = view.findViewById(R.id.edit_text_login_sign_up);
        buttonEnterignUp =  view.findViewById(R.id.btn_enter_sign_up);

        buttonEnterignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String S = editTextLoginSignUp.getText().toString();
                Intent intent = new Intent(BROADCAST_ACTION);
                intent.putExtra(KEY_FROM_FRAGMENT_SIGN_UP, S);
                getActivity().getApplicationContext().sendBroadcast(intent);
            }
        });


        return view;
    }
}

