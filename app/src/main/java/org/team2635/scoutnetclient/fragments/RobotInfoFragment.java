package org.team2635.scoutnetclient.fragments;



import android.app.Activity;
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
    protected View mView;
    private CheckBox[] checkBoxReferences = new CheckBox[8];

    public RobotInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_robot_info,container,false);

        this.mView = v;

        return v;
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Robot Capabilities");

        super.onStart();
    }



   public void getCheckBoxReferences()
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

            checkBoxReferences[0] = S1;
            checkBoxReferences[1] = S2;
            checkBoxReferences[2] = S3;
            checkBoxReferences[3] = S4;
            checkBoxReferences[4] = S5;
            checkBoxReferences[5] = S6;
            checkBoxReferences[6] = S7;
            checkBoxReferences[7] = S8;
    }

    public String[] getCheckBoxData()
    {
        getCheckBoxReferences();
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
            position++;
        }

        return selections;
    }

    public String[] getOptions()
    {
        String[] options = new String[8];

        options[0] = "R1";
        options[1] = "R2";
        options[2] = "R3";
        options[3] = "R4";
        options[4] = "R5";
        options[5] = "R6";
        options[6] = "R7";
        options[7] = "R8";

        return options;
    }

    public String getNumberOfWheels()
    {
        EditText picker = (EditText) getActivity().findViewById(R.id.numberOfWheels);
        String i = picker.getText().toString();
        return i;
    }

    public String getLocomotionType()
    {
        String returnString = "";
        RadioGroup group = (RadioGroup) mView.findViewById(R.id.locomotionType);


        int id = group.getCheckedRadioButtonId();

        if(id == -1)
        {
            //No button checked, not to worry
            returnString = "";
        }
        else
        {
            String idString = getResources().getResourceEntryName(id);
            returnString = idString.substring(4);
        }


        return returnString;
    }

    public String getUsingVision()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.usesVision);
        String returnString = "";
        int id = group.getCheckedRadioButtonId();

        if(id == -1)
        {
            //No option selected, don't cry yet
            returnString = "";
        }
        else
        {
            String idString = getResources().getResourceEntryName(id);
            returnString = idString.substring(6);
        }


        return returnString;
    }

    public String getVisionUsage()
    {
        EditText usage = (EditText) getActivity().findViewById(R.id.visionUsage);
        return usage.getText().toString();
    }

    public String getUsingAuto()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.usesAuto);
        String returnString = "";
        int id = group.getCheckedRadioButtonId();

        if(id == -1)
        {
            //No option selected, but we shouldn't get depressed. Probably.
            returnString = "";
        }
        else
        {
            String idString = getResources().getResourceEntryName(id);
            returnString = idString.substring(4);
        }


        return returnString;
    }

    public String getAutoUsage()
    {
        EditText usage = (EditText) getActivity().findViewById(R.id.autoUsage);
        return usage.getText().toString();
    }

    public String getDriveTrain()
    {
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.driveTrain);
        String returnString = "";
        int id = group.getCheckedRadioButtonId();

        if(id == -1)
        {
            //Fine! Maybe you didn't want to select anything. See if I care! I'm only a computer anyways...
            returnString = "";
        }
        else
        {
            String idString = getResources().getResourceEntryName(id);
            returnString = idString.substring(5);
        }


        return returnString;
    }
}
