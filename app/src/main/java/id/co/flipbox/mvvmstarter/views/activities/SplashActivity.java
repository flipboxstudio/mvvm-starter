package id.co.flipbox.mvvmstarter.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import id.co.flipbox.mvvmstarter.utils.constants.I;


public class SplashActivity extends BaseActivity
{
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //wait time, simulate any data that you need to fetch before going into main activities
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run ()
            {
                Intent mainIntent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, I.SPLASH_DISPLAY_LENGTH);
    }
}
