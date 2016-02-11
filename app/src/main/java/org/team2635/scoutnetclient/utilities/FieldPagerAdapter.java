package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.AutonomousInfoFragment;
import org.team2635.scoutnetclient.fragments.FieldInfoFragment;
import org.team2635.scoutnetclient.fragments.MatchDefensesFragment;
import org.team2635.scoutnetclient.fragments.TeamInfoFragment;
import org.team2635.scoutnetclient.fragments.RobotInfoFragment;
import org.team2635.scoutnetclient.fragments.TeleopInfoFragment;

public class FieldPagerAdapter extends FragmentPagerAdapter {

    public FieldPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {

        //TODO: Test field view pager
        switch (arg0) {
            case 0:
                return new FieldInfoFragment();
            case 1:
                return new AutonomousInfoFragment();
            case 2:
                return new TeleopInfoFragment();
            case 3:
                return new MatchDefensesFragment();
            default:
                break;
        }
        return new Fragment();
   }

    @Override
    public int getCount() {

        return 3;
    }

}

