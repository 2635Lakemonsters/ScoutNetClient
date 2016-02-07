package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.TeamInfoFragment;
import org.team2635.scoutnetclient.fragments.RobotInfoFragment;

public class FieldPagerAdapter extends FragmentPagerAdapter {

    public FieldPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {

        //TODO: Change to actual fragments
        switch (arg0) {
            case 0:
                return new TeamInfoFragment();
            case 1:
                return new RobotInfoFragment();
            default:
                break;
        }
        return new Fragment();
   }

    @Override
    public int getCount() {

        return 2;
    }

}

