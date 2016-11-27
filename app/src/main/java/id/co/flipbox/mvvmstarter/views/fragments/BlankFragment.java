package id.co.flipbox.mvvmstarter.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.co.flipbox.mvvmstarter.R;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class BlankFragment extends BaseFragment
{
    public BlankFragment ()
    {
        setArguments(new Bundle());
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    void initUI ()
    {

    }

    @Override
    void initEvent ()
    {

    }
}
