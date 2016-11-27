package id.co.flipbox.mvvmstarter.views.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.views.adapters.ViewPagerAdapter;
import id.co.flipbox.mvvmstarter.views.fragments.BlankFragment;
import id.co.flipbox.mvvmstarter.views.fragments.ListFragment;


public class ViewPagerActivity extends AppCompatActivity
{

    TabLayout mTabLayout;
    ViewPager mViewPager;
    List<Fragment> mFragments;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        mFragments = new ArrayList<>();
        mFragments.add(new BlankFragment());
        mFragments.add(new ListFragment());

        // do we need to implement databinding on each layout? I don't think so. but feel free to bind the layout if you want to.
        mViewPager = (ViewPager) findViewById(R.id.vp_fragments_container);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mFragments));

        mTabLayout = (TabLayout) findViewById(R.id.tl_tabs_container);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
