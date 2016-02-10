package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.team2635.scoutnetclient.PitInfoActivity;
import org.team2635.scoutnetclient.R;
import org.w3c.dom.Text;


public class TeleopInfoFragment extends Fragment implements View.OnClickListener
{
    public int highScores = 0;
    public int lowScores = 0;
    public int defenseCrosses = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleHighGoalAdd);
        Button button2 = (Button) view.findViewById(R.id.teleHighGoalSub);
        Button button3 = (Button) view.findViewById(R.id.teleLowAdd);
        Button button4 = (Button) view.findViewById(R.id.teleLowSub);
        Button button5 = (Button) view.findViewById(R.id.teleDefenseAdd);
        Button button6 = (Button) view.findViewById(R.id.teleDefenseSub);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        // Set title bar
        ((PitInfoActivity) getActivity())
                .setActionBarTitle("Teleop Activities");
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleHighGoalAdd:
                ++highScores;
                break;
            case R.id.teleHighGoalSub:
                if(highScores > 0)
                {
                    --highScores;
                }
                break;
            case R.id.teleLowAdd:
                ++lowScores;
                break;
            case R.id.teleLowSub:
                if(lowScores > 0)
                {
                    --lowScores;
                }
                break;
            case R.id.teleDefenseAdd:
                ++defenseCrosses;
                break;
            case R.id.teleDefenseSub:
                if(defenseCrosses > 0)
                {
                    --defenseCrosses;
                }
                break;
        }
        updateCounts();
    }

    public void updateCounts()
    {
        TextView high = (TextView) getActivity().findViewById(R.id.teleopHighGoalNum);
        TextView low = (TextView) getActivity().findViewById(R.id.teleopLowGoalNum);
        TextView defense = (TextView) getActivity().findViewById(R.id.teleDefenseNum);
        high.setText(highScores);
        low.setText(lowScores);
        defense.setText(defenseCrosses);
    }

    public String getHighScores()
    {
        return Integer.toString(highScores);
    }

    public String getLowScores()
    {
        return Integer.toString(lowScores);
    }

    public String getDefenseCrosses()
    {
        return Integer.toString(defenseCrosses);
    }

    public String towerChallenged()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.didChallenge);
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

    public String towerScaled()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.didScale);
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

    public String defenseBreached()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.didBreach);
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


}
