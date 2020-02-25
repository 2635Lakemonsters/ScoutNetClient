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
    private int upperCells = 0;
    private int lowerCells = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleUpperAdd);
        Button button2 = (Button) view.findViewById(R.id.teleUpperSub);
        Button button3 = (Button) view.findViewById(R.id.teleLowerAdd);
        Button button4 = (Button) view.findViewById(R.id.teleLowerSub);



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
                    .setActionBarTitle("Teleop Info");
            TextView upper = (TextView) getActivity().findViewById(R.id.teleUpperNumber);
            TextView lower = (TextView) getActivity().findViewById(R.id.teleLowerNumber);

            upper.setText(String.valueOf(upperCells));
            lower.setText(String.valueOf(lowerCells));

        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleUpperAdd:
                ++upperCells;
                break;
            case R.id.teleUpperSub:
                if(upperCells > 0)
                {
                    --upperCells;
                }
            case R.id.teleLowerAdd:
                ++lowerCells;
                break;
            case R.id.teleLowerSub:
                if(lowerCells > 0)
                {
                    --lowerCells;
                }
                break;


        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView upper = (TextView) getActivity().findViewById(R.id.teleUpperNumber);
        TextView lower = (TextView) getActivity().findViewById(R.id.teleLowerNumber);

        upper.setText(String.valueOf(upperCells));
        lower.setText(String.valueOf(lowerCells));


    }

    public String getUpperCells()
    {
        return Integer.toString(upperCells);
    }
    public String getLowerCells()
    {
        return Integer.toString(lowerCells);
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

    public String usesPanel()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.usesPanel);
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
