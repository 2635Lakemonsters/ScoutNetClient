package org.team2635.scoutnetclient.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import org.team2635.scoutnetclient.FieldInfoActivity;
import org.team2635.scoutnetclient.R;

public class MatchDefensesFragment extends Fragment
{
    protected View mView;
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


        super.onStart();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser)
    {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser)
        {
            ((FieldInfoActivity) getActivity())
                    .setActionBarTitle("Defenses");
        }
    }

    public String getStory(){
        EditText astory = (EditText) getActivity().findViewById(R.id.storyTime);
        return astory.getText().toString();
    }

    public String getEndgame()
    {
        String returnString = "";
        RadioGroup group = (RadioGroup) getActivity().findViewById(R.id.endgame);


        int id = group.getCheckedRadioButtonId();

        System.out.println("getEndgame()");

        if(id == -1)
        {
            System.out.println("id = -1");
            //No button checked, not to worry
            returnString = "";
        }
        else
        {
            System.out.println("else");
            String idString = getResources().getResourceEntryName(id);
            returnString = idString;
        }
        System.out.println("return");
        return returnString;
    }

    public String getLevel()
    {
        CheckBox box = (CheckBox) getActivity().findViewById(R.id.switchLevel);
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
