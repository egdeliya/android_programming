package com.example.delya.fragmentwork;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Delya on 17.10.2017.
 */

public class MySecondFragment extends Fragment {

    OnSecondFragmentInteraction mCallback;
    ViewGroup mContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_second, container, false);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            if (context instanceof OnSecondFragmentInteraction) {
                mCallback = (OnSecondFragmentInteraction) context;
                Button button = (Button) getActivity().findViewById(R.id.my_second_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mCallback.onSecondFragmentInteraction();
                    }
                });
            }
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public interface OnSecondFragmentInteraction {
        public void onSecondFragmentInteraction();
    }

    public void onClick(View view) {
        mCallback.onSecondFragmentInteraction();
    }

}
