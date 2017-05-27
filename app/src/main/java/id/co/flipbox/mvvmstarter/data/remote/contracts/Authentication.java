package id.co.flipbox.mvvmstarter.data.remote.contracts;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface Authentication
{
    void login (String id, String password);
    void logout ();
    void forgotPassword (String id);
}
