package com.example.delya.fragmentwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MyFragment.OnFragmentInteraction,
        MySecondFragment.OnSecondFragmentInteraction{

    @Override
    public void onFragmentInteraction() {
        MySecondFragment mySecondFragment = new MySecondFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, mySecondFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSecondFragmentInteraction() {
        MyFragment myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragment myFragment = new MyFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myFragment).commit();
    }

//    public void onClick(View v) {
//        MySecondFragment mySecondFragment = new MySecondFragment();
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, mySecondFragment)
//                .addToBackStack(null)
//                .commit();
//
//    }
}
