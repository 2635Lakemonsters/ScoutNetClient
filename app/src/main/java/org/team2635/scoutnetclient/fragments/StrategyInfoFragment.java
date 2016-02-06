package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.team2635.scoutnetclient.PitInfoActivity;
import org.team2635.scoutnetclient.R;

public class StrategyInfoFragment extends Fragment
{
    //TODO: Test this class's functionality
    public CheckBox[] references = new CheckBox[9];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_strategy_info,container,false);
    }

    @Override
    public void onStart()
    {
        getReferences.start();

        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Strategies");
    }

    public Thread getReferences = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
            //For reference sheet, see github wiki page: Strategy info fragment
            CheckBox S1 = (CheckBox) getActivity().findViewById(R.id.doesChallengeTower);
            CheckBox S2 = (CheckBox) getActivity().findViewById(R.id.doesScaleTower);
            CheckBox S3 = (CheckBox) getActivity().findViewById(R.id.doesBreachDefenses);
            CheckBox S4 = (CheckBox) getActivity().findViewById(R.id.doesHoldSally);
            CheckBox S5 = (CheckBox) getActivity().findViewById(R.id.doesHoldBridge);
            CheckBox S6 = (CheckBox) getActivity().findViewById(R.id.doesLowGoal);
            CheckBox S7 = (CheckBox) getActivity().findViewById(R.id.doesHighGoal);
            CheckBox S8 = (CheckBox) getActivity().findViewById(R.id.isDefensive);
            CheckBox S9 = (CheckBox) getActivity().findViewById(R.id.isOffensive);

            references[1] = S1;
            references[2] = S2;
            references[3] = S3;
            references[4] = S4;
            references[5] = S5;
            references[6] = S6;
            references[7] = S7;
            references[8] = S8;
            references[9] = S9;
        }
    });

    public String[] getData()
    {
        int position = 0;
        String[] selections = new String[10];
        EditText generalNotes = (EditText) getActivity().findViewById(R.id.generalStrategyNotes);

        //Iterate through references[], check if reference is checked, and commit that to selections[position]
        for(CheckBox s: references)
        {
            //Putting a human-readable string value instead of boolean
            if(s.isChecked())
            {
                selections[position] = "Yes";
            }
            else
            {
                selections[position] = "No";
            }
        }

        selections[10] = generalNotes.getText().toString();

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[10];

        options[1] = "S1";
        options[2] = "S2";
        options[3] = "S3";
        options[4] = "S4";
        options[5] = "S5";
        options[6] = "S6";
        options[7] = "S7";
        options[8] = "S8";
        options[9] = "S9";
        options[10] = "GENERALNOTES";

        return options;
    }
}
