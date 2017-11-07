package com.github.a5809909.hwork06_fragmentservicereceiver.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.a5809909.hwork06_fragmentservicereceiver.MainActivity;
import com.github.a5809909.hwork06_fragmentservicereceiver.R;

public class FragmentSignIn extends Fragment {


    private Button buttonEnterignIn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_layout, container, false);
        view.findViewById(R.id.btn_enter_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getActivity() != null) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.ControlServiceActivity();
                }

            }

            ;
        });
        return view;
    }
}

