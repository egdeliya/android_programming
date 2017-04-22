package com.example.delya.abcfragments;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends FragmentActivity
        implements AFragment.OnButtonClickListener, C2Fragment.OnDecreaseButtonClickListener, C1Fragment.OnIncreaseButtonClickListener {
    protected static int mCount = 0;
    protected static String ARG_COUNT = "0";
    final String LOG_TAG = "myLogs";

    public void switchToC2Fragment(){
        //user click to the Button to switch to C2 fragment

        AFragment aFrag = (AFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        BFragment bFrag = (BFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container1);

        C2Fragment newC2Frag  = new C2Fragment();
        Bundle args = new Bundle();
        args.putInt(newC2Frag.ARG_COUNT, mCount);
        newC2Frag.setArguments(args);

        if (bFrag != null) {

            aFrag.deactivateC2button();

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

            trans.replace(R.id.fragment_container2, newC2Frag, "C2 fragment");

            trans.commit();

            aFrag.activateC1button();
        } else {

            // Otherwise, we're in the one-pane layout and must swap frags..
            BFragment newBFrag  = new BFragment();
            Bundle argsB = new Bundle();
            argsB.putInt(newBFrag.ARG_COUNT, mCount);
            newBFrag.setArguments(argsB);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container2, newC2Frag, "C2 fragment");
            transaction.replace(R.id.fragment_container1, newBFrag, "B fragment");
            transaction.remove(aFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
        newC2Frag.updateCounter(mCount);
    }

    public void switchToC1Fragment(){

        AFragment aFrag = (AFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        BFragment bFrag = (BFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container1);

        C1Fragment newC1Frag  = new C1Fragment();
        Bundle args = new Bundle();
        args.putInt(newC1Frag.ARG_COUNT, mCount);
        newC1Frag.setArguments(args);

        if (bFrag != null) {
            aFrag.deactivateC1button();

            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            trans.replace(R.id.fragment_container2, newC1Frag, "C1 fragment");
            trans.commit();

            aFrag.activateC2button();
        } else {

            // Otherwise, we're in the one-pane layout and must swap frags...

            BFragment newBFrag  = new BFragment();
            Bundle argsB = new Bundle();
            argsB.putInt(newBFrag.ARG_COUNT, mCount);
            newBFrag.setArguments(argsB);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container2, newC1Frag, "C1 fragment");
            transaction.replace(R.id.fragment_container1, newBFrag, "B fragment");
            transaction.remove(aFrag);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

        newC1Frag.updateCounter(mCount);
    }

    public void changeCounter(int count) {
        // Print counter in B Fragment
        BFragment bFrag = (BFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_container1);

        bFrag.setText(count);
        mCount = count;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log.d(LOG_TAG, "Activity on create");


        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                mCount = savedInstanceState.getInt(ARG_COUNT);
            }

            if ((getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) ==
                    Configuration.SCREENLAYOUT_SIZE_LARGE ) {

                // если экран большой
                AFragment aFragment = new AFragment();
                BFragment bFragment = new BFragment();

                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                trans.add(R.id.fragment_container, aFragment, "A fragment");
                trans.add(R.id.fragment_container1, bFragment, "B fragment");

                trans.commit();

            } else {
                // если экран

                BFragment bFrag = (BFragment)
                        getSupportFragmentManager().findFragmentById(R.id.fragment_container1);
                if (bFrag == null) {
                    // Create a new Fragments to be placed in the activity layout
                    AFragment aFragment = new AFragment();

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.fragment_container, aFragment, "A Fragment").commit();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(ARG_COUNT, mCount);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOG_TAG, "Activity on start");

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        mCount = savedInstanceState.getInt(ARG_COUNT);
    }
}

