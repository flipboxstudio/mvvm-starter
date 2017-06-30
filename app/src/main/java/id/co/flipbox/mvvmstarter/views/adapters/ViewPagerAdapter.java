package id.co.flipbox.mvvmstarter.views.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragments;

    public ViewPagerAdapter (FragmentManager fm)
    {
        super(fm);
        mFragments = new ArrayList<>();
    }

    public ViewPagerAdapter (FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem (int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount ()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle (int position)
    {
        // TODO: implement your own page title.
        return mFragments.get(position).getClass().getSimpleName();
    }
}
