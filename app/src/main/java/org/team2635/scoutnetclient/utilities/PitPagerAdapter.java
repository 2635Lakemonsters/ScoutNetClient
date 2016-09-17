package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.TeamInfoFragment;
import org.team2635.scoutnetclient.fragments.DefensesFragment;
import org.team2635.scoutnetclient.fragments.StrategyInfoFragment;
import org.team2635.scoutnetclient.fragments.RobotInfoFragment;

public class PitPagerAdapter extends FragmentPagerAdapter
{

    TeamInfoFragment teamFrag = new TeamInfoFragment();
    RobotInfoFragment robotFrag = new RobotInfoFragment();
    //StrategyInfoFragment strategyFrag = new StrategyInfoFragment();
    //DefensesFragment defenseFrag = new DefensesFragment();

    public PitPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {

        switch (arg0) {
            case 0:
                return teamFrag;
            case 1:
                return robotFrag;
            //case 2:
                //return strategyFrag;
            //case 3:
               // return defenseFrag;
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

