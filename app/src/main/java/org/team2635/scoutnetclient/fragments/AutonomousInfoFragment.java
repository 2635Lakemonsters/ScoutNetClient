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
    private int highScores;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoHighAdd);
        Button button2 = (Button) view.findViewById(R.id.autoHighSub);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((FieldInfoActivity) getActivity())
                .setActionBarTitle("Autonomous Info");

        super.onStart();
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoHighAdd:
                ++highScores;
                break;
            case R.id.autoHighSub:
                if(highScores > 0)
                {
                    --highScores;
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

    public String defenseCrossed()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.matchDefenseCrossed);
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

    public String ownBucketLifted()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.liftOwnBucket);
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

    public String enemyBucketLifted()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.liftEnemyBucket);
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

    public String getHighScores()
    {
        return Integer.toString(highScores);
    }

    private void updateCounts()
    {
        TextView high = (TextView) getActivity().findViewById(R.id.autoHighGoalNumber);
        high.setText(String.valueOf(highScores));
    }
}
