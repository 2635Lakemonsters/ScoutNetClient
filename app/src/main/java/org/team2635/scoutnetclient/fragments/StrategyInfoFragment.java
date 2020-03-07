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
    private CheckBox[] references = new CheckBox[4];

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
            CheckBox S1 = (CheckBox) getActivity().findViewById(R.id.doesAutomaticallyAim);
            CheckBox S2 = (CheckBox) getActivity().findViewById(R.id.doesAutoShoot);
            CheckBox S3 = (CheckBox) getActivity().findViewById(R.id.doesAutoPickUp);
            CheckBox S4 = (CheckBox) getActivity().findViewById(R.id.doesShootFromOtherSide);



            references[0] = S1;
            references[1] = S2;
            references[2] = S3;
            references[3] = S4;

        }

    public String[] getData()
    {
        getReferences();
        int position = 0;
        String[] selections = new String[5];
        EditText generalNotes = (EditText) getActivity().findViewById(R.id.generalStrategyNotes);

        //Iterate through references[], check if reference is checked, and commit that to selections[position]
        for(CheckBox s: references)
        {
            if(position > 3){
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

        selections[4] = generalNotes.getText().toString();

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[5];

        options[0] = "DOESAUTOMATICAIM";
        options[1] = "DOESAUTOSHOOT";
        options[2] = "DOESAUTOPICKUP";
        options[3] = "DOESSHOOTFROMOPPOSITE";
        options[4] = "GENERALNOTES";

        return options;
    }
}
