package id.co.flipbox.mvvmstarter.views.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.views.fragments.ForgotPasswordFragment;
import id.co.flipbox.mvvmstarter.views.fragments.LoginFragment;

public class AuthActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentInteractionListener,
        ForgotPasswordFragment.OnForgotFragmentInteractionListener
{

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    LoginFragment loginFragment = new LoginFragment();
    ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // init first fragment
        ft = fm.beginTransaction();
        ft.add(R.id.fl_fragment_container, loginFragment);
        ft.commit();

    }

    @Override
    public void onForgotPasswordClick ()
    {
        showForgotPassword();
    }

    @Override
    public void onBackPressed ()
    {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
            finish();
        }
        else
        {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void showLoginForm ()
    {
        //clear backstack
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        ft = fm.beginTransaction();
        ft.replace(R.id.fl_fragment_container, loginFragment);
        ft.commit();
    }

    public void showForgotPassword ()
    {
        ft = fm.beginTransaction();
        ft.replace(R.id.fl_fragment_container, forgotPasswordFragment);
        ft.addToBackStack("");
        ft.commit();
    }
}


