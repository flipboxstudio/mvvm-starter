package id.co.flipbox.mvvmstarter.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;

import id.co.flipbox.mvvmstarter.data.DataManager;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment
{

    EventBus    event;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        event = EventBus.getDefault();
    }

    /**
     * method used for first UI initialization & manipulation
     */
    abstract void initUI ();

    /**
     * method used for first event initialization & manipulation
     */
    abstract void initEvent ();

    protected void hideKeyboard ()
    {
        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
