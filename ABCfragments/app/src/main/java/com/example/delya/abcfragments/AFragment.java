package com.example.delya.abcfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AFragment extends Fragment {
    OnButtonClickListener mCallback;
    final String LOG_TAG = "myLogs";

    // Container Activity must implement this interface
    public interface OnButtonClickListener {
        public void switchToC1Fragment();
        public void switchToC2Fragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.afragment, container, false);
        Button buttonC2 = (Button) v.findViewById(R.id.button_switch_to_c2);
        buttonC2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.switchToC2Fragment();
            }
        });
        Button buttonC1 = (Button) v.findViewById(R.id.button_switch_to_c1);
        buttonC1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.switchToC1Fragment();
            }
        });

        //Log.d(LOG_TAG, "ARagment on create View");
        //Log.d(LOG_TAG, "ARagment isShown "+v.());

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        //Log.d(LOG_TAG, "ARagment on pause");

        (getView().findViewById(R.id.button_switch_to_c1))
                .setEnabled(false);
        (getView().findViewById(R.id.button_switch_to_c2))
                .setEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Log.d(LOG_TAG, "ARagment on start");

//        ((Button)getView().findViewById(R.id.button_switch_to_c1))
//                .setEnabled(true);
//        ((Button)getView().findViewById(R.id.button_switch_to_c2))
//                .setEnabled(true);
    }

    @Override
    public void onResume() {
        super.onResume();

        //Log.d(LOG_TAG, "ARagment on resume");

        (getView().findViewById(R.id.button_switch_to_c1))
                .setEnabled(true);
        (getView().findViewById(R.id.button_switch_to_c2))
                .setEnabled(true);
    }

    public void deactivateC2button() {
        (getView().findViewById(R.id.button_switch_to_c2))
                .setEnabled(false);
    }

    public void activateC2button() {
        (getView().findViewById(R.id.button_switch_to_c2))
                .setEnabled(true);
    }

    public void deactivateC1button() {
        (getView().findViewById(R.id.button_switch_to_c1))
                .setEnabled(false);
    }

    public void activateC1button() {
        (getView().findViewById(R.id.button_switch_to_c1))
                .setEnabled(true);
    }

}
