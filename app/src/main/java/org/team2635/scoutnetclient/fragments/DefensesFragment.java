package org.team2635.scoutnetclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import org.team2635.scoutnetclient.PitInfoActivity;
import org.team2635.scoutnetclient.R;

public class DefensesFragment extends Fragment
{
    private RadioGroup[] groups;
    private String[] selections;
    private static final String TAG = " Pit Defenses Fragment";

    public DefensesFragment()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_crossable_defenses, container,false);
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Crossable Defenses");

        super.onStart();
    }

    private void getGroups()
    {
        RadioGroup A1 = (RadioGroup) getActivity().findViewById(R.id.A1);
        RadioGroup A2 = (RadioGroup) getActivity().findViewById(R.id.A2);
        RadioGroup B1 = (RadioGroup) getActivity().findViewById(R.id.B1);
        RadioGroup B2 = (RadioGroup) getActivity().findViewById(R.id.B2);
        RadioGroup C1 = (RadioGroup) getActivity().findViewById(R.id.C1);
        RadioGroup C2 = (RadioGroup) getActivity().findViewById(R.id.C2);
        RadioGroup D1 = (RadioGroup) getActivity().findViewById(R.id.D1);
        RadioGroup D2 = (RadioGroup) getActivity().findViewById(R.id.D2);
        RadioGroup LB = (RadioGroup) getActivity().findViewById(R.id.Lowbar);

        groups = new RadioGroup[9];

        groups[0] = A1;
        groups[1] = A2;
        groups[2] = B1;
        groups[3] = B2;
        groups[4] = C1;
        groups[5] = C2;
        groups[6] = D1;
        groups[7] = D2;
        groups[8] = LB;

    }

    private void readData()
    {
        selections = new String[9];
        int position = 0;
        for(RadioGroup g : groups)
        {
            //Gets checked buttons ID, converts it to a string, then extracts ending bit (Yes, Unknown, or No)
            int id = g.getCheckedRadioButtonId();
            if(id == -1)
            {
                //No button was selected, this just prevents the app from crashing, belive it or not
                selections[position] = "";
            }
            else
            {
                //Button selected, good to proceed!
                String idString = getResources().getResourceEntryName(id);
                String substring = idString.substring(2);
                //Commits substring to position in array
                selections[position] = substring;
            }
            position++;
        }

    }

    public String[] getSelections()
    {
        getGroups();
        readData();
        return selections;
    }

    public String[] getDefenses()
    {
        String[] defenses = new String[9];

        defenses[0] = "A1";
        defenses[1] = "A2";
        defenses[2] = "B1";
        defenses[3] = "B2";
        defenses[4] = "C1";
        defenses[5] = "C2";
        defenses[6] = "D1";
        defenses[7] = "D2";
        defenses[8] = "LB";

        return defenses;
    }

}
