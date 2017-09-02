package com.example.delya.achievementsapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.squareup.picasso.Picasso;

/**
 * Created by Delya on 02.09.2017.
 */

public class ProfileFragment extends Fragment implements OnRequestSocialPersonCompleteListener {

    private static final String NETWORK_ID = "NETWORK_ID";
    private SocialNetwork socialNetwork;
    private int networkId;
    private ImageView photo;
    private TextView name;

    public ProfileFragment() {

    }

    public static ProfileFragment newInstannce(int id) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putInt(NETWORK_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        networkId = getArguments().containsKey(NETWORK_ID) ? getArguments().getInt(NETWORK_ID) : 0;
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Profile");

        View rootView = inflater.inflate(R.layout.profile_fragment, container, false);

        photo = (ImageView) rootView.findViewById(R.id.imageView);
        name = (TextView) rootView.findViewById(R.id.name);
        colorProfile(networkId);

        socialNetwork = MainFragment.mSocialNetworkManager.getSocialNetwork(networkId);
        socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
        socialNetwork.requestCurrentPerson();

        MainActivity.showProgress("Loading social person");
        return rootView;

    }

    @Override
    public void onRequestSocialPersonSuccess(int networkId, SocialPerson socialPerson) {
        name.setText(socialPerson.name);
        Picasso.with(getActivity())
                .load(socialPerson.avatarURL)
                .into(photo);
    }

    @Override
    public void onError(int networkId, String requestID, String errorMessage, Object data) {
        Toast.makeText(getActivity(), "ERROR: " + errorMessage, Toast.LENGTH_LONG).show();
    }

    private void colorProfile(int networkId){
        int color = getResources().getColor(R.color.blue);
        int image = R.drawable.user;
        color = getResources().getColor(R.color.dark_blue);
        image = R.drawable.vk_user;
        name.setTextColor(color);
        photo.setBackgroundColor(color);
        photo.setImageResource(image);
    }
}
