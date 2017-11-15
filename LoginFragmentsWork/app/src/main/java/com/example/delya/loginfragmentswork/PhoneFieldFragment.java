package com.example.delya.loginfragmentswork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class PhoneFieldFragment extends Fragment {

    private static String TAG = "Fragment phone field";
    private static String PHONE_NUMBER = "";

    private EditText phoneField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_phone_field, container, false);
        phoneField = v.findViewById(R.id.phoneFieldEditText);

        if (savedInstanceState != null) {
            String phoneNumber = savedInstanceState.getString(PHONE_NUMBER);
            phoneField.setText(phoneNumber);
        }

        return v;
    }

    public String getPhoneNumber() {
        String phoneNumber = phoneField.getText().toString().trim();
        return phoneNumber;
    }
}
