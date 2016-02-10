package org.team2635.scoutnetclient.fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import org.team2635.scoutnetclient.PitInfoActivity;
import org.team2635.scoutnetclient.R;

public class RobotInfoFragment extends Fragment
{
    //TODO: Test this
    public CheckBox[] checkBoxReferences = new CheckBox[8];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_robot_info,container,false);
    }

    @Override
    public void onStart()
    {
        getCheckBoxReferences.start();

        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Robot Capabilities");
    }

    public Thread getCheckBoxReferences = new Thread(new Runnable()
    {
        @Override
        public void run()
        {

            //For reference sheet, see github wiki page: Robot info fragment
            CheckBox S1 = (CheckBox) getActivity().findViewById(R.id.canShootLow);
            CheckBox S2 = (CheckBox) getActivity().findViewById(R.id.canShootHigh);
            CheckBox S3 = (CheckBox) getActivity().findViewById(R.id.canHoldDrawbridge);
            CheckBox S4 = (CheckBox) getActivity().findViewById(R.id.canHoldSallyPort);
            CheckBox S5 = (CheckBox) getActivity().findViewById(R.id.canChallenge);
            CheckBox S6 = (CheckBox) getActivity().findViewById(R.id.canScale);
            CheckBox S7 = (CheckBox) getActivity().findViewById(R.id.canPlayDefense);
            CheckBox S8 = (CheckBox) getActivity().findViewById(R.id.canPlayOffense);

            checkBoxReferences[1] = S1;
            checkBoxReferences[2] = S2;
            checkBoxReferences[3] = S3;
            checkBoxReferences[4] = S4;
            checkBoxReferences[5] = S5;
            checkBoxReferences[6] = S6;
            checkBoxReferences[7] = S7;
            checkBoxReferences[8] = S8;
        }
    });

    public String[] getCheckBoxData()
    {
        int position = 0;
        String[] selections = new String[8];

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
        }

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[8];

        options[1] = "B1";
        options[2] = "B2";
        options[3] = "B3";
        options[4] = "B4";
        options[5] = "B5";
        options[6] = "B6";
        options[7] = "B7";
        options[8] = "B8";

        return options;
    }

    public String getNumberOfWheels()
    {
        NumberPicker picker = (NumberPicker) getActivity().findViewById(R.id.numberOfWheels);
        int i = picker.getValue();
        String num = Integer.toString(i);

        return num;
    }

    public String getLocomotionType()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.locomotionType);
        int id = group.getCheckedRadioButtonId();

        String idString = getResources().getResourceEntryName(id);
        String substring = idString.substring(5);
        String type = substring;

        return type;
    }

    public String getUsingVision()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.usesVision);
        int id = group.getCheckedRadioButtonId();

        String idString = getResources().getResourceEntryName(id);
        String substring = idString.substring(7);

        return substring;
    }

    public String getVisionUsage()
    {
        EditText usage = (EditText) getActivity().findViewById(R.id.visionUsage);
        String string = usage.getText().toString();
        return string;
    }

    public String getUsingAuto()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.usesAuto);
        int id = group.getCheckedRadioButtonId();

        String idString = getResources().getResourceEntryName(id);
        String substring = idString.substring(5);

        return substring;
    }

    public String getAutoUsage()
    {
        EditText usage = (EditText) getActivity().findViewById(R.id.autoUsage);
        String string = usage.getText().toString();
        return string;
    }

    public String getDriveTrain()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.driveTrain);
        int id = group.getCheckedRadioButtonId();

        String idString = getResources().getResourceEntryName(id);
        String substring = idString.substring(6);
        String type = substring;

        return type;
    }
}
