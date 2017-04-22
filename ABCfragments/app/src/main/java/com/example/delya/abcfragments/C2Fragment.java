package com.example.delya.abcfragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class C2Fragment extends Fragment {
    protected static String ARG_COUNT = "0";
    protected static int mCount = 0;
    OnDecreaseButtonClickListener mCallback;
    final String LOG_TAG = "myLogs";

    // Container Activity must implement this interface
    public interface OnDecreaseButtonClickListener {
        public void changeCounter(int count);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (C2Fragment.OnDecreaseButtonClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnButtonClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.c2fragment, container, false);

        // If activity recreated (such as from screen rotate), restore
        // the previous counter set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(ARG_COUNT);
        }

        Button buttonC2 = (Button) v.findViewById(R.id.button_decrease);
        buttonC2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mCallback.changeCounter(--mCount);
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        // Save the current counter selection in case we need to recreate the fragment
        outState.putInt(ARG_COUNT, mCount);
        super.onSaveInstanceState(outState);

    }

    public void updateCounter(int count) {
        mCount = count;
    }

//        @Override
//    public void onStart() {
//        super.onStart();
//
//        Log.d(LOG_TAG, "counter in c1 is"+mCount);
//
//        Bundle args = getArguments();
//        if (args != null) {
//            mCount = args.getInt(ARG_COUNT);
//        }
//
//    }
}
