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
    private CheckBox[] references = new CheckBox[9];

    public StrategyInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_strategy_info,container,false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            ((PitInfoActivity) getActivity())
                    .setActionBarTitle("Strategies");
        }
    }

    private void getReferences()
    {
            //For reference sheet, see github wiki page: Strategy info fragment
            CheckBox S1 = (CheckBox) getActivity().findViewById(R.id.doesSwitch);
            CheckBox S2 = (CheckBox) getActivity().findViewById(R.id.doesScale);
            CheckBox S3 = (CheckBox) getActivity().findViewById(R.id.doesLiftCubes);
            CheckBox S4 = (CheckBox) getActivity().findViewById(R.id.doesShootCubes);
            CheckBox S5 = (CheckBox) getActivity().findViewById(R.id.doesVault);
            CheckBox S6 = (CheckBox) getActivity().findViewById(R.id.doesClimb);
            CheckBox S7 = (CheckBox) getActivity().findViewById(R.id.isDefensive);
            CheckBox S8 = (CheckBox) getActivity().findViewById(R.id.isOffensive);

            references[0] = S1;
            references[1] = S2;
            references[2] = S3;
            references[3] = S4;
            references[4] = S5;
            references[5] = S6;
            references[6] = S7;
            references[7] = S8;
        }

    public String[] getData()
    {
        getReferences();
        int position = 0;
        String[] selections = new String[9];
        EditText generalNotes = (EditText) getActivity().findViewById(R.id.generalStrategyNotes);

        //Iterate through references[], check if reference is checked, and commit that to selections[position]
        for(CheckBox s: references)
        {
            if(position > 7){
                break;
            }
            //Putting a human-readable string value instead of boolean
            if(s.isChecked())
            {
                selections[position] = "Yes";
            }
            else
            {
                selections[position] = "No";
            }
            position++;
        }

        selections[8] = generalNotes.getText().toString();

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[9];

        options[0] = "S1";
        options[1] = "S2";
        options[2] = "S3";
        options[3] = "S4";
        options[4] = "S5";
        options[5] = "S6";
        options[6] = "S7";
        options[7] = "S8";
        options[8] = "GENERALNOTES";

        return options;
    }
}
