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
    private int cratesFilled = 0;
    private int bunniesPlaced = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleCrateAdd);
        Button button2 = (Button) view.findViewById(R.id.teleCrateSub);
        Button button5 = (Button) view.findViewById(R.id.teleBunnyAdd);
        Button button6 = (Button) view.findViewById(R.id.teleBunnySub);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
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
            TextView CratesFilled = (TextView) getActivity().findViewById(R.id.teleopCratesFilledNum);
            TextView BunniesPlaced = (TextView) getActivity().findViewById(R.id.teleopBunniesPlacedNum);

            CratesFilled.setText(String.valueOf(cratesFilled));
            BunniesPlaced.setText(String.valueOf(bunniesPlaced));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleCrateAdd:
                ++cratesFilled;
                break;
            case R.id.teleCrateSub:
                if(cratesFilled > 0)
                {
                    --cratesFilled;
                }
                break;
            case R.id.teleBunnyAdd:
                ++bunniesPlaced;
                break;
            case R.id.teleBunnySub:
                if(bunniesPlaced > 0)
                {
                    --bunniesPlaced;
                }
                break;

        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView CratesFilled = (TextView) getActivity().findViewById(R.id.teleopCratesFilledNum);
        TextView BunniesPlaced = (TextView) getActivity().findViewById(R.id.teleopBunniesPlacedNum);

        CratesFilled.setText(String.valueOf(cratesFilled));

        BunniesPlaced.setText(String.valueOf(bunniesPlaced));

    }

    public String getCratesFilled()
    {
        return Integer.toString(cratesFilled);
    }
    public String getBunniesPlaced()
    {
        return Integer.toString(bunniesPlaced);
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
