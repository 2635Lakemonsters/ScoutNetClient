package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.TeamInfoFragment;
import org.team2635.scoutnetclient.fragments.DefensesFragment;
import org.team2635.scoutnetclient.fragments.StrategyInfoFragment;
import org.team2635.scoutnetclient.fragments.RobotInfoFragment;

public class PitPagerAdapter extends FragmentPagerAdapter {

    public PitPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int arg0) {

        switch (arg0) {
            case 0:
                return new TeamInfoFragment();
            case 1:
                return new RobotInfoFragment();
            case 2:
                return new StrategyInfoFragment();
            case 3:
                return new DefensesFragment();
            default:
                break;
        }
        return new Fragment();
   }

    @Override
    public int getCount() {

        return 4;
    }

    public String getFragmentReference(int viewId, int index)
    {
        return makeFragmentName(viewId, index);
    }

    private String makeFragmentName(int viewPagerId, int index)
    {
        return "android:switcher:"+ viewPagerId + ":" + index;
    }

}

