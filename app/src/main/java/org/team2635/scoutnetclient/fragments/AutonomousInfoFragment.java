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
    private int bunniesSupported = 0;
    private int tubsContacted = 0;

    public AutonomousInfoFragment()
    {
        //Required
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autonomous_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.autoBunniesAdd);
        Button button2 = (Button) view.findViewById(R.id.autoBunniesSub);
        Button button3 = (Button) view.findViewById(R.id.autoTubsAdd);
        Button button4 = (Button) view.findViewById(R.id.autoTubsSub);


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
            TextView bunnies = (TextView) getActivity().findViewById(R.id.autoBunniesNumber);
            TextView tubs = (TextView) getActivity().findViewById(R.id.autoTubsNumber);

            bunnies.setText(String.valueOf(bunniesSupported));
            tubs.setText(String.valueOf(tubsContacted));
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.autoBunniesAdd:
                ++bunniesSupported;
                break;
            case R.id.autoBunniesSub:
                if(bunniesSupported > 0)
                {
                    --bunniesSupported;
                }
                break;
            case R.id.autoTubsAdd:
                ++tubsContacted;
                break;
            case R.id.autoTubsSub:
                if(tubsContacted > 0)
                {
                    --tubsContacted;
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

    public String supportsTub()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.supportsTub);
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

    public String getBunniesSupported()
    {
        return Integer.toString(bunniesSupported);
    }
    public String getTubsContacted() { return Integer.toString(tubsContacted);}

    private void updateCounts()
    {
        TextView bunnies = (TextView) getActivity().findViewById(R.id.autoBunniesNumber);
        TextView tubs = (TextView) getActivity().findViewById(R.id.autoTubsNumber);

        bunnies.setText(String.valueOf(bunniesSupported));
        tubs.setText(String.valueOf(tubsContacted));
    }
}
