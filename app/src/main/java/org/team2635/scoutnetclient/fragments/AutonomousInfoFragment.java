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
    private int upperCells = 0;
    private int lowerCells = 0;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoUpperAdd);
        Button button2 = (Button) view.findViewById(R.id.autoUpperSub);
        Button button3 = (Button) view.findViewById(R.id.autoLowerAdd);
        Button button4 = (Button) view.findViewById(R.id.autoLowerSub);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


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
            TextView upper = (TextView) getActivity().findViewById(R.id.autoUpperNumber);
            TextView lower = (TextView) getActivity().findViewById(R.id.autoLowerNumber);

            upper.setText(String.valueOf(upperCells));
            lower.setText(String.valueOf(lowerCells));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoUpperAdd:
                ++upperCells;
                break;
            case R.id.autoUpperSub:
                if(upperCells > 0)
                {
                    --upperCells;
                }
                break;
            case R.id.autoLowerAdd:
                ++lowerCells;
                break;
            case R.id.autoLowerSub:
                if(lowerCells > 0)
                {
                    --lowerCells;
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

    public String crossesLine()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.crossesLine);
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

    public String shootsCells()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.shootsCells);
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

    public String collectsCells()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.collectsCells);
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

    public String getUpperCells()
    {
        return Integer.toString(upperCells);
    }
    public String getLowerCells() { return Integer.toString(lowerCells);}

    private void updateCounts()
    {
        TextView upper = (TextView) getActivity().findViewById(R.id.autoUpperNumber);
        TextView lower = (TextView) getActivity().findViewById(R.id.autoLowerNumber);

        upper.setText(String.valueOf(upperCells));
        lower.setText(String.valueOf(lowerCells));
    }
}
