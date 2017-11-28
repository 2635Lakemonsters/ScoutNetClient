package org.team2635.scoutnetclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;

public class MatchDefensesFragment extends Fragment
{
    private CheckBox[] checkBoxReferences = new CheckBox[9];

    public MatchDefensesFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_match_defenses, container, false);
    }

    @Override
    public void onStart()
    {

        // Set title bar
        ((FieldInfoActivity) getActivity())
                .setActionBarTitle("Defenses");

        super.onStart();
    }

    public String getStory(){
        EditText astory = (EditText) getActivity().findViewById(R.id.storyTime);
        return astory.getText().toString();
    }


}
