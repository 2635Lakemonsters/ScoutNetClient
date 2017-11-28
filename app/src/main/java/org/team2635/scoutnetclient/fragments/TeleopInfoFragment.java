package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;


public class TeleopInfoFragment extends Fragment implements View.OnClickListener
{
    private int highScores = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleHighGoalAdd);
        Button button2 = (Button) view.findViewById(R.id.teleHighGoalSub);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((FieldInfoActivity) getActivity())
                .setActionBarTitle("Teleop Activities");

        super.onStart();
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleHighGoalAdd:
                ++highScores;
                break;
            case R.id.teleHighGoalSub:
                if(highScores > 0)
                {
                    --highScores;
                }
                break;

        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView high = (TextView) getActivity().findViewById(R.id.teleopHighGoalNum);

        high.setText(String.valueOf(highScores));

    }

    public String getHighScores()
    {
        return Integer.toString(highScores);
    }

    public String didMalfunction()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.didMalfunction);
        String toReturn;
        if(box.isChecked())
        {
            toReturn = "Yes";
        }
        else
        {
            toReturn = "No";
        }
        return toReturn;
    }

    public String foundBunny()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.foundBunny);
        String toReturn;
        if(box.isChecked())
        {
            toReturn = "Yes";
        }
        else
        {
            toReturn = "No";
        }
        return toReturn;
    }

    public String stoleBunny()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.stoleBunny);
        String toReturn;
        if(box.isChecked())
        {
            toReturn = "Yes";
        }
        else
        {
            toReturn = "No";
        }
        return toReturn;
    }
    public String getNotes(){
        EditText notes = (EditText) getActivity().findViewById(R.id.teleNotes);
        return notes.getText().toString();
    }

}
