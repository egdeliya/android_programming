package com.example.delya.loginfragmentswork;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SendSmsFragment extends ActionBaseFragment {

    private static final String TAG = "Call fragment";
    private static final String PHONE_NUMBER = "phone";
    private int MY_PERMISSIONS_REQUEST_CALL_PHONE;

    private String mPhoneNumber;
    private Button sendSmsBtn;
    private EditText mMessageEditText;

    ActionBaseFragment.OnActionIntentListener mCallIntentListener;

    public SendSmsFragment() {}

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
        View v = inflater.inflate(R.layout.fragment_send_sms, container, false);
        sendSmsBtn = v.findViewById(R.id.buttonSendSms);
        mMessageEditText = v.findViewById(R.id.editTextMessage);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        sendSmsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhoneNumber = mCallIntentListener.getPhoneNumber();
                Toast.makeText(getContext(), "phone number in call fragments " + mPhoneNumber, Toast.LENGTH_SHORT)
                        .show();

                String message = mMessageEditText.getText().toString().trim();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + mPhoneNumber));
                intent.putExtra("sms_body", message);
                startActivity(intent);
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save the current phone number in case we need to recreate the fragment
        outState.putString(PHONE_NUMBER, mPhoneNumber);
        super.onSaveInstanceState(outState);
    }
}
