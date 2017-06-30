package id.co.flipbox.mvvmstarter.data;

import id.co.flipbox.mvvmstarter.data.local.UserStorage;
import id.co.flipbox.mvvmstarter.data.remote.AuthAPI;
import id.co.flipbox.mvvmstarter.data.remote.UserAPI;

/**
 * Created by bukhoriaqid on 11/10/16.
 */

public class DataManager implements DataManagerType
{
    private static DataManager dm;

    public static DataManager can () // or use, or call (?)
    {
        if (dm == null)
        {
            dm = new DataManager();
        }
        return dm;
    }

    private static AuthAPI     sAuthAPI     = new AuthAPI();
    private static UserAPI     sUserAPI     = new UserAPI();
    private static UserStorage sUserStorage = new UserStorage();

    @Override
    public void login (String id, String password)
    {
        sAuthAPI.login(id, password);
    }

    @Override
    public void logout ()
    {
        sAuthAPI.logout();
    }

    @Override
    public void forgotPassword (String id) { sAuthAPI.forgotPassword(id);}

    @Override
    public void getUserList ()
    {
        sUserAPI.getList();
    }

    @Override
    public void getUser (Integer id)
    {
        sUserAPI.read(id);
    }

    @Override
    public String getUserToken ()
    {
        return sUserStorage.getToken();
    }
}
