package id.co.flipbox.mvvmstarter.viewmodels;

import id.co.flipbox.mvvmstarter.data.DataManager;

/**
 * Created by bukhoriaqid on 11/10/16.
 */

public class AuthViewModel extends BaseViewModel
{
    String userId, password;

    public void login ()
    {
        DataManager.login(userId, password);
    }
}
