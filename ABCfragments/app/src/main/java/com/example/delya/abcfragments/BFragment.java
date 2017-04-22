package com.example.delya.abcfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BFragment extends Fragment {
    protected static String ARG_COUNT = "0";
    protected static int mCount = 0;
    final String LOG_TAG = "myLogs";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.bfragment, container, false);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(ARG_COUNT);
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((TextView)getView().findViewById(R.id.text_view_counter))
        .setText("Counter is " + mCount);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save the current counter selection in case we need to recreate the fragment
        outState.putInt(ARG_COUNT, mCount);
        super.onSaveInstanceState(outState);
    }

    public void setText(int counter) {
        mCount = counter;
        TextView t = (TextView) getView().findViewById(R.id.text_view_counter);  //UPDATE
        t.setText("Counter is "+ mCount);
    }
}
