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
    private int linesCrossed;
    private int bunnyScores;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoLineAdd);
        Button button2 = (Button) view.findViewById(R.id.autoLineSub);
        Button button3 = (Button) view.findViewById(R.id.autoBunnyAdd);
        Button button4 = (Button) view.findViewById(R.id.autoBunnySub);

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
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoLineAdd:
                ++linesCrossed;
                break;
            case R.id.autoLineSub:
                if(linesCrossed > 0)
                {
                    --linesCrossed;
                }
                break;
            case R.id.autoBunnyAdd:
                ++bunnyScores;
                break;
            case R.id.autoBunnySub:
                if(bunnyScores > 0)
                {
                    --bunnyScores;
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

    public String getLinesCrossed()
    {
        return Integer.toString(linesCrossed);
    }

    public String getBunnyScores()
    {
        return Integer.toString(bunnyScores);
    }

    private void updateCounts()
    {
        TextView high = (TextView) getActivity().findViewById(R.id.autoLineNumber);
        TextView low = (TextView) getActivity().findViewById(R.id.autoBunnyNumber);
        high.setText(String.valueOf(linesCrossed));
        low.setText(String.valueOf(bunnyScores));
    }
}
