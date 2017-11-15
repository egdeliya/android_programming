package com.example.delya.loginfragmentswork;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class CallFragment extends ActionBaseFragment {

    private static final String TAG = "Call fragment";
    private static final String PHONE_NUMBER = "phone";
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE;

    private String mPhoneNumber;
    private Button callBtn;

    OnActionIntentListener mCallIntentListener;

    public CallFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoneNumber = getArguments().getString(PHONE_NUMBER);
        }
        Log.d(TAG, ""+mPhoneNumber);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call, container, false);
        callBtn = v.findViewById(R.id.buttonCall);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneNumber = mCallIntentListener.getPhoneNumber();
                Toast.makeText(getContext(), "phone number in call fragments " + mPhoneNumber, Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mPhoneNumber));

                // проверяем разрешение на звонки на этапе выполнения
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                } else {
                    // we already have permission
                    try {
                        startActivity(intent);
                    } catch(SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallIntentListener= (ActionBaseFragment.OnActionIntentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickListener");
        }
    }

//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        // сохраняем теку
//        outState.putString(PHONE_NUMBER, mPhoneNumber);
//        super.onSaveInstanceState(outState);
//    }

}
