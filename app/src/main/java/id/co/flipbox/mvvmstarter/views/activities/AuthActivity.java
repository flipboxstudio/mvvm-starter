package id.co.flipbox.mvvmstarter.views.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import java.util.List;

import id.co.flipbox.mvvmstarter.R;
import id.co.flipbox.mvvmstarter.utils.constants.I;
import id.co.flipbox.mvvmstarter.utils.constants.S;
import id.co.flipbox.mvvmstarter.views.fragments.ForgotPasswordFragment;
import id.co.flipbox.mvvmstarter.views.fragments.LoginFragment;
import pub.devrel.easypermissions.EasyPermissions;

public class AuthActivity extends BaseActivity implements LoginFragment.OnLoginFragmentInteractionListener,
        ForgotPasswordFragment.OnForgotFragmentInteractionListener, EasyPermissions.PermissionCallbacks
{

    FragmentManager        fm                     = getSupportFragmentManager();
    ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
    FragmentTransaction    ft                     = fm.beginTransaction();
    LoginFragment          loginFragment          = new LoginFragment();

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        //request location permission early
        if (!EasyPermissions.hasPermissions(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION))
        {
            EasyPermissions
                    .requestPermissions(AuthActivity.this, S.location_permission_message, I.LOCATION_REQUEST_CODE,
                                        Manifest.permission.ACCESS_FINE_LOCATION);
        }

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

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(I.LOCATION_REQUEST_CODE, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted (int requestCode, List<String> perms)
    {
        if (requestCode == I.LOCATION_REQUEST_CODE)
        {
            Toast.makeText(getApplicationContext(), R.string.permission_granted, Toast.LENGTH_LONG).show();
            super.initLocationDetection();
        }
    }

    @Override
    public void onPermissionsDenied (int requestCode, List<String> perms)
    {
        if (requestCode == I.LOCATION_REQUEST_CODE)
        {
            Toast.makeText(getApplicationContext(), R.string.permission_denied, Toast.LENGTH_LONG).show();
        }
    }

    public void showForgotPassword ()
    {
        ft = fm.beginTransaction();
        ft.replace(R.id.fl_fragment_container, forgotPasswordFragment);
        ft.addToBackStack("");
        ft.commit();
    }
}


