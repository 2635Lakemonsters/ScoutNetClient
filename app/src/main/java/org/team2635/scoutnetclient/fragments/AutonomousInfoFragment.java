package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;

public class AutonomousInfoFragment extends Fragment implements View.OnClickListener
{
    private int highScores;
    private int lowScores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoHighAdd);
        Button button2 = (Button) view.findViewById(R.id.autoHighSub);
        Button button3 = (Button) view.findViewById(R.id.autoLowAdd);
        Button button4 = (Button) view.findViewById(R.id.autoLowSub);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((FieldInfoActivity) getActivity())
                .setActionBarTitle("Autonomous Info");
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoHighAdd:
                ++highScores;
                break;
            case R.id.autoHighSub:
                if(highScores > 0)
                {
                    --highScores;
                }
                break;
            case R.id.autoLowAdd:
                ++lowScores;
                break;
            case R.id.autoLowSub:
                if(lowScores > 0)
                {
                    --lowScores;
                }
                break;
        }
        updateCounts();
    }

    public String getAutonomous()
    {
        Switch autoSwitch = (Switch) getActivity().findViewById(R.id.matchDoesAutonomous);
        String toReturn;
        if(autoSwitch.isChecked())
        {
            toReturn = "Yes";
        }
        else
        {
            toReturn = "No";
        }

        return toReturn;
    }

    public String defenseReached()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.matchDefenseReached);
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

    public String defenseCrossed()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.matchDefenseCrossed);
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

    public String getHighScores()
    {
        return Integer.toString(highScores);
    }

    public String getLowScores()
    {
        return Integer.toString(lowScores);
    }

    private void updateCounts()
    {
        TextView high = (TextView) getActivity().findViewById(R.id.autoHighGoalNumber);
        TextView low = (TextView) getActivity().findViewById(R.id.autoLowGoalNumber);
        high.setText(highScores);
        low.setText(lowScores);
    }
}
