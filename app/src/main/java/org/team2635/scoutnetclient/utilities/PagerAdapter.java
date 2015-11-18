package org.team2635.scoutnetclient.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.team2635.scoutnetclient.fragments.BasicInfoFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        switch (arg0) {
            case 0:
                return new BasicInfoFragment();

            default:
                break;
        }
        return new Fragment();
   }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }

}

