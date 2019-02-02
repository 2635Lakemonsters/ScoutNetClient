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
    private int cargoDelivered = 0;
    private int panelsAttached = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleCargoAdd);
        Button button2 = (Button) view.findViewById(R.id.teleCargoSub);
        Button button5 = (Button) view.findViewById(R.id.telePanelAdd);
        Button button6 = (Button) view.findViewById(R.id.telePanelSub);




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
            TextView cargo = (TextView) getActivity().findViewById(R.id.teleCargoNum);
            TextView BunniesPlaced = (TextView) getActivity().findViewById(R.id.telePanelNum);

            cargo.setText(String.valueOf(cargoDelivered));
            BunniesPlaced.setText(String.valueOf(panelsAttached));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleCargoAdd:
                ++cargoDelivered;
                break;
            case R.id.teleCargoSub:
                if(cargoDelivered > 0)
                {
                    --cargoDelivered;
                }
                break;
            case R.id.telePanelAdd:
                ++panelsAttached;
                break;
            case R.id.telePanelSub:
                if(panelsAttached > 0)
                {
                    --panelsAttached;
                }
                break;

        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView cargo = (TextView) getActivity().findViewById(R.id.teleCargoNum);
        TextView panels = (TextView) getActivity().findViewById(R.id.telePanelNum);

        cargo.setText(String.valueOf(cargoDelivered));

        panels.setText(String.valueOf(panelsAttached));

    }

    public String getCargoDelivered()
    {
        return Integer.toString(cargoDelivered);
    }
    public String getPanelsAttached()
    {
        return Integer.toString(panelsAttached);
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
