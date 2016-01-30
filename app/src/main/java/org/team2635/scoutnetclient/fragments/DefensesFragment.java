package org.team2635.scoutnetclient.fragments;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import org.team2635.scoutnetclient.R;

import java.lang.reflect.Array;

public class DefensesFragment extends Fragment
{
    OnHeadlineSelectedListener mCallback;
    RadioGroup[] groups;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_crossable_defenses, container,false);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onStart()
    {
        groupGetThread.start();
    }

    // Container Activity must implement this interface
    public interface OnHeadlineSelectedListener
    {
        public void onArticleSelected(int position);
    }

    public Thread groupGetThread = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
            RadioGroup A1 = (RadioGroup) getActivity().findViewById(R.id.A1);
            RadioGroup A2 = (RadioGroup) getActivity().findViewById(R.id.A2);
            RadioGroup B1 = (RadioGroup) getActivity().findViewById(R.id.B1);
            RadioGroup B2 = (RadioGroup) getActivity().findViewById(R.id.B2);
            RadioGroup C1 = (RadioGroup) getActivity().findViewById(R.id.C1);
            RadioGroup C2 = (RadioGroup) getActivity().findViewById(R.id.C2);
            RadioGroup D1 = (RadioGroup) getActivity().findViewById(R.id.D1);
            RadioGroup D2 = (RadioGroup) getActivity().findViewById(R.id.D2);
            RadioGroup LB = (RadioGroup) getActivity().findViewById(R.id.Lowbar);

            groups = new RadioGroup[9];

            groups[0] = A1;
            groups[1] = A2;
            groups[3] = B1;
            groups[4] = B2;
            groups[5] = C1;
            groups[6] = C2;
            groups[7] = D1;
            groups[8] = D2;
            groups[9] = LB;
        }
    });


    public void readData()
    {
        for(RadioGroup g : groups)
        {
            int id = g.getCheckedRadioButtonId();

            switch(id)
            {


            }
        }

    }

}
