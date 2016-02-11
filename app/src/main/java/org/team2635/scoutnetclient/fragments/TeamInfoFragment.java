package org.team2635.scoutnetclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.team2635.scoutnetclient.PitInfoActivity;
import org.team2635.scoutnetclient.R;

public class TeamInfoFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_teaminfo,container,false);
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Basic Info");
    }

    public String getTeamNumber()
    {
        EditText number = (EditText) getActivity().findViewById(R.id.teamNumber);
        String string = number.getText().toString();
        return string;
    }

    public String getTeamName()
    {
        EditText name = (EditText) getActivity().findViewById(R.id.teamName);
        String string = name.getText().toString();
        return string;
    }

    public String getRobotName()
    {
        EditText name = (EditText) getActivity().findViewById(R.id.robotName);
        String string = name.getText().toString();
        return string;
    }
}
