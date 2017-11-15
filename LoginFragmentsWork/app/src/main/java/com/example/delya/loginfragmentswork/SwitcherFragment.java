package com.example.delya.loginfragmentswork;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SwitcherFragment extends Fragment {
    OnButtonClickListener mCallback;
    final String LOG = "SwitcherFragment";

    public interface OnButtonClickListener {
        void switchToCallFragment();
        void switchToSendSmsFragment();
    }

    private Button mBtnCall;
    private Button mBtnSendSms;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_switcher, container, false);

        mBtnCall = v.findViewById(R.id.button_switch_to_call_fragment);
        mBtnCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.switchToCallFragment();
            }
        });

        mBtnSendSms = v.findViewById(R.id.button_switch_to_sms_fragment);
        mBtnSendSms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.switchToSendSmsFragment();
            }
        });

        return v;
    }

    public void changeActiveButton(boolean isCallEnabled) {
        if (isCallEnabled) {
            mBtnCall.setEnabled(false);
            mBtnSendSms.setEnabled(true);
        } else {
            mBtnSendSms.setEnabled(false);
            mBtnCall.setEnabled(true);
        }
    }
}
