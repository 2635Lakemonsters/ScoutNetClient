package org.team2635.scoutnetclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;
@Deprecated
public class MatchDefensesFragment extends Fragment
{
    private CheckBox[] checkBoxReferences = new CheckBox[9];

    public MatchDefensesFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_defenses, container, false);
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
                    .setActionBarTitle("Defenses");
        }
    }

    private void getCheckBoxReferences()
    {
            //For reference sheet, see github wiki page: Robot info fragment
            CheckBox S1 = (CheckBox) getActivity().findViewById(R.id.MatchA1);
            CheckBox S2 = (CheckBox) getActivity().findViewById(R.id.MatchA2);
            CheckBox S3 = (CheckBox) getActivity().findViewById(R.id.MatchB1);
            CheckBox S4 = (CheckBox) getActivity().findViewById(R.id.MatchB2);
            CheckBox S5 = (CheckBox) getActivity().findViewById(R.id.MatchC1);
            CheckBox S6 = (CheckBox) getActivity().findViewById(R.id.MatchC2);
            CheckBox S7 = (CheckBox) getActivity().findViewById(R.id.MatchD1);
            CheckBox S8 = (CheckBox) getActivity().findViewById(R.id.MatchD2);
            CheckBox S9 = (CheckBox) getActivity().findViewById(R.id.MatchLB);

            checkBoxReferences[0] = S1;
            checkBoxReferences[1] = S2;
            checkBoxReferences[2] = S3;
            checkBoxReferences[3] = S4;
            checkBoxReferences[4] = S5;
            checkBoxReferences[5] = S6;
            checkBoxReferences[6] = S7;
            checkBoxReferences[7] = S8;
            checkBoxReferences[8] = S9;
    }


    public String[] getCheckBoxData()
    {
        getCheckBoxReferences();
        int position = 0;
        String[] selections = new String[9];

        //Iterate through checkBoxReferences[], check if reference is checked, and commit that to selections[position]
        for(CheckBox s: checkBoxReferences)
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
            position++;
        }

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[8];

        options[0] = "B1";
        options[1] = "B2";
        options[2] = "B3";
        options[3] = "B4";
        options[4] = "B5";
        options[5] = "B6";
        options[6] = "B7";
        options[7] = "B8";

        return options;
    }
}
