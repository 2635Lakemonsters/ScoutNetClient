package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;

public class FieldInfoFragment extends Fragment {

    public FieldInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_info, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            ((FieldInfoActivity) getActivity())
                    .setActionBarTitle("Basic Info");
        }
    }

    public String getTeamNum()
    {
        EditText picker = (EditText) getActivity().findViewById(R.id.fieldTeamNumber);
        return picker.getText().toString();
    }

    public String getMatchNum()
    {
        EditText usage = (EditText) getActivity().findViewById(R.id.fieldMatchNumber);
        return usage.getText().toString();
    }

    public String getDefenseRating()
    {
        RatingBar bar = (RatingBar) getActivity().findViewById(R.id.defensiveRating);
        float i = bar.getRating();
        return Float.toString(i);
    }

}
