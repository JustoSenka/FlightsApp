package com.justing.flights.home;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import com.justing.flights.objects.AppData;

/**
 * Created by JustInG on 5/9/2016.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(FragmentManager fm) { super(fm); }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SearchFragment.newInstance();
            case 1:
                return MyFlightsFragment.newInstance();
            case 2:
                return ConsultantsFragment.newInstance();
        }
        return MyFlightsFragment.newInstance();
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        if (AppData.getInstance().getCurrentUser().isRegisteredUser())
            return 3;
        else
            return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Search";
            case 1:
                return "My Flights";
            case 2:
                return "Consultants";
        }
        return null;
    }
}
