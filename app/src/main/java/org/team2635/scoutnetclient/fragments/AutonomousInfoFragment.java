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
    private int cargoDelivered = 0;
    private int panelsAttached = 0;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoCargoAdd);
        Button button2 = (Button) view.findViewById(R.id.autoCargoSub);
        Button button3 = (Button) view.findViewById(R.id.autoPanelAdd);
        Button button4 = (Button) view.findViewById(R.id.autoPanelSub);


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
            TextView cargo = (TextView) getActivity().findViewById(R.id.autoCargoNumber);
            TextView panels = (TextView) getActivity().findViewById(R.id.autoPanelNumber);

            cargo.setText(String.valueOf(cargoDelivered));
            panels.setText(String.valueOf(panelsAttached));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoCargoAdd:
                ++cargoDelivered;
                break;
            case R.id.autoCargoSub:
                if(cargoDelivered > 0)
                {
                    --cargoDelivered;
                }
                break;
            case R.id.autoPanelAdd:
                ++panelsAttached;
                break;
            case R.id.autoPanelSub:
                if(panelsAttached > 0)
                {
                    --panelsAttached;
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

    public String getCargoDelivered()
    {
        return Integer.toString(cargoDelivered);
    }
    public String getPanelsAttached() { return Integer.toString(panelsAttached);}

    private void updateCounts()
    {
        TextView cargo = (TextView) getActivity().findViewById(R.id.autoCargoNumber);
        TextView panels = (TextView) getActivity().findViewById(R.id.autoPanelNumber);

        cargo.setText(String.valueOf(cargoDelivered));
        panels.setText(String.valueOf(panelsAttached));
    }
}
