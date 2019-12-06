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
    private int bedCubes = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleCubeAdd);
        Button button2 = (Button) view.findViewById(R.id.teleCubeSub);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

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
            TextView cubes = (TextView) getActivity().findViewById(R.id.teleCubeNumber);

            cubes.setText(String.valueOf(bedCubes));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleCubeAdd:
                ++bedCubes;
                break;
            case R.id.teleCubeSub:
                if(bedCubes > 0)
                {
                    --bedCubes;
                }
                break;


        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView cubes = (TextView) getActivity().findViewById(R.id.teleCubeNumber);

        cubes.setText(String.valueOf(bedCubes));


    }

    public String getBedCubes()
    {
        return Integer.toString(bedCubes);
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

    public String doesGive()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.doesGive);
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
