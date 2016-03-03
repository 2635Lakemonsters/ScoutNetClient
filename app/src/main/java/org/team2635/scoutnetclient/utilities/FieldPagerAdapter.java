package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.AutonomousInfoFragment;
import org.team2635.scoutnetclient.fragments.FieldInfoFragment;
import org.team2635.scoutnetclient.fragments.MatchDefensesFragment;
import org.team2635.scoutnetclient.fragments.TeleopInfoFragment;

public class FieldPagerAdapter extends FragmentPagerAdapter
{
    FieldInfoFragment fieldInfoFragment = new FieldInfoFragment();
    AutonomousInfoFragment autonomousInfoFragment = new AutonomousInfoFragment();
    TeleopInfoFragment teleopInfoFragment = new TeleopInfoFragment();
    MatchDefensesFragment matchDefensesFragment = new MatchDefensesFragment();

    public FieldPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {

        //TODO: Test field view pager
        switch (arg0) {
            case 0:
                return fieldInfoFragment;
            case 1:
                return autonomousInfoFragment;
            case 2:
                return teleopInfoFragment;
            case 3:
                return matchDefensesFragment;
            default:
                break;
        }
        return new Fragment();
   }

    @Override
    public int getCount() {

        return 4;
    }
}

