package id.co.flipbox.mvvmstarter;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.common.api.GoogleApiClient;

import id.co.flipbox.mvvmstarter.data.remote.retrofit.MyAPIService;
import id.co.flipbox.mvvmstarter.data.remote.retrofit.RetrofitServiceFactory;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class MyApplication extends Application
{
    private static MyApplication   sApp;
    public         MyAPIService    mAPIService;
    public         GoogleApiClient mGoogleApiClient;
    public         Location        mLastLocation;

    public static MyApplication getInstance ()
    {
        if (sApp == null)
        {
            sApp = new MyApplication();
        }

        return sApp;
    }

    @Override
    public void onCreate ()
    {
        super.onCreate();

        sApp = this;
        mAPIService = RetrofitServiceFactory.createService(MyAPIService.class);
    }

    public boolean isNetworkAvailable ()
    {
        ConnectivityManager lConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo         lNetworkInfo         = lConnectivityManager.getActiveNetworkInfo();
        return lNetworkInfo != null && lNetworkInfo.isConnected();
    }
}
