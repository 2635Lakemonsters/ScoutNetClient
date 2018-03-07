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
    private int OSwitchScores = 0;
    private int ScaleScores = 0;
    private int ESwitchScores = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleOSwitchGoalAdd);
        Button button2 = (Button) view.findViewById(R.id.teleOSwitchGoalSub);
        Button button3 = (Button) view.findViewById(R.id.teleScaleGoalAdd);
        Button button4 = (Button) view.findViewById(R.id.teleScaleGoalSub);
        Button button5 = (Button) view.findViewById(R.id.teleESwitchGoalAdd);
        Button button6 = (Button) view.findViewById(R.id.teleESwitchGoalSub);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        return view;
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
                    .setActionBarTitle("Teleop Info");
            TextView OSwitch = (TextView) getActivity().findViewById(R.id.teleopOSwitchGoalNum);
            TextView Scale = (TextView) getActivity().findViewById(R.id.teleopScaleGoalNum);
            TextView ESwitch = (TextView) getActivity().findViewById(R.id.teleopESwitchGoalNum);

            OSwitch.setText(String.valueOf(OSwitchScores));
            Scale.setText(String.valueOf(ScaleScores));
            ESwitch.setText(String.valueOf(ESwitchScores));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleOSwitchGoalAdd:
                ++OSwitchScores;
                break;
            case R.id.teleOSwitchGoalSub:
                if(OSwitchScores > 0)
                {
                    --OSwitchScores;
                }
                break;
            case R.id.teleScaleGoalAdd:
                ++ScaleScores;
                break;
            case R.id.teleScaleGoalSub:
                if(ScaleScores > 0)
                {
                    --ScaleScores;
                }
                break;
            case R.id.teleESwitchGoalAdd:
                ++ESwitchScores;
                break;
            case R.id.teleESwitchGoalSub:
                if(ESwitchScores > 0)
                {
                    --ESwitchScores;
                }
                break;

        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView OSwitch = (TextView) getActivity().findViewById(R.id.teleopOSwitchGoalNum);
        TextView Scale = (TextView) getActivity().findViewById(R.id.teleopScaleGoalNum);
        TextView ESwitch = (TextView) getActivity().findViewById(R.id.teleopESwitchGoalNum);

        OSwitch.setText(String.valueOf(OSwitchScores));
        Scale.setText(String.valueOf(ScaleScores));
        ESwitch.setText(String.valueOf(ESwitchScores));

    }

    public String getOSwitchScores()
    {
        return Integer.toString(OSwitchScores);
    }
    public String getScaleScores()
    {
        return Integer.toString(ScaleScores);
    }
    public String getESwitchScores()
    {
        return Integer.toString(ESwitchScores);
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

    public String doesClimb()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.doesClimb);
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

    public String doesVault()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.doesVault);
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

    public String doesDefend()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.doesDefend);
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
