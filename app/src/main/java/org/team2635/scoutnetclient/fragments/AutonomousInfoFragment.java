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
    private int cratesFilled = 0;
    //private int lowScores = 0;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoCrateAdd);
        Button button2 = (Button) view.findViewById(R.id.autoCrateSub);
//        Button button3 = (Button) view.findViewById(R.id.autoLowAdd);
//        Button button4 = (Button) view.findViewById(R.id.autoLowSub);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);


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
                    .setActionBarTitle("Autonomous Info");
            TextView crates = (TextView) getActivity().findViewById(R.id.autoCrateNumber);
            //TextView low = (TextView) getActivity().findViewById(R.id.autoLowGoalNumber);

            crates.setText(String.valueOf(cratesFilled));
            //low.setText(String.valueOf(lowScores));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoCrateAdd:
                ++cratesFilled;
                break;
            case R.id.autoCrateSub:
                if(cratesFilled > 0)
                {
                    --cratesFilled;
                }
                break;
//            case R.id.autoLowAdd:
//                ++lowScores;
//                break;
//            case R.id.autoLowSub:
//                if(lowScores > 0)
//                {
//                    --lowScores;
//                }
//                break;
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

    public String centerCrossed()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.matchCenterCrossed);
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

    public String autoBroke()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.autoBroke);
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

    public String getCratesFilled()
    {
        return Integer.toString(cratesFilled);
    }
    //public String getLowScores() { return Integer.toString(lowScores);}

    private void updateCounts()
    {
        TextView crates = (TextView) getActivity().findViewById(R.id.autoCrateNumber);
        //TextView low = (TextView) getActivity().findViewById(R.id.autoLowGoalNumber);

        crates.setText(String.valueOf(cratesFilled));
        //low.setText(String.valueOf(lowScores));
    }
}
