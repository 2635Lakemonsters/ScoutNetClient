package org.team2635.scoutnetclient.fragments;


import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;


public class TeleopInfoFragment extends Fragment implements View.OnClickListener
{
    private int bunnies = 0;
    private int shotDarts = 0;
    private int hitDarts = 0;
    private int lineCrosses = 0;


    public TeleopInfoFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teleop_info, container, false);

        Button button1 = (Button) view.findViewById(R.id.teleBunniesAdd);
        Button button2 = (Button) view.findViewById(R.id.teleBunniesSub);
        Button button3 = (Button) view.findViewById(R.id.teleShotDartsAdd);
        Button button4 = (Button) view.findViewById(R.id.teleShotDartsSub);
        Button button5 = (Button) view.findViewById(R.id.teleLinesAdd);
        Button button6 = (Button) view.findViewById(R.id.teleLinesSub);
        Button button7 = (Button) view.findViewById(R.id.teleHitDartsAdd);
        Button button8 = (Button) view.findViewById(R.id.teleHitDartsSub);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

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
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.teleBunniesAdd:
                ++bunnies;
                break;
            case R.id.teleBunniesSub:
                if(bunnies > 0)
                {
                    --bunnies;
                }
                break;
            case R.id.teleShotDartsAdd:
                ++shotDarts;
                break;
            case R.id.teleShotDartsSub:
                if(shotDarts > 0)
                {
                    --shotDarts;
                }
                break;
            case R.id.teleLinesAdd:
                ++lineCrosses;
                break;
            case R.id.teleLinesSub:
                if(lineCrosses > 0)
                {
                    --lineCrosses;
                }
                break;
            case R.id.teleHitDartsAdd:
                ++hitDarts;
                break;
            case R.id.teleHitDartsSub:
                if(hitDarts > 0)
                {
                    --hitDarts;
                }
                break;
            default:
                break;
        }
        updateCounts();
    }

    private void updateCounts()
    {
        TextView bunniesCounter = (TextView) getActivity().findViewById(R.id.teleopBunniesNum);
        TextView shotDartsCounter = (TextView) getActivity().findViewById(R.id.teleopShotDartsNum);
        TextView linesCounter = (TextView) getActivity().findViewById(R.id.teleLinesNum);
        TextView hitDartsCounter = (TextView) getActivity().findViewById(R.id.teleHitDarts);

        bunniesCounter.setText(String.valueOf(bunnies));
        shotDartsCounter.setText(String.valueOf(shotDarts));
        linesCounter.setText(String.valueOf(lineCrosses));
        hitDartsCounter.setText(String.valueOf(hitDarts));
    }

    public String getBunnies()
    {
        return Integer.toString(bunnies);
    }

    public String getShotDarts()
    {
        return Integer.toString(shotDarts);
    }

    public String getHitDarts()
    {
        return Integer.toString(hitDarts);
    }

    public String getLineCrosses()
    {
        return Integer.toString(lineCrosses);
    }
}
