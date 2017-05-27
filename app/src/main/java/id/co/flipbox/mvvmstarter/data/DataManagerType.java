package id.co.flipbox.mvvmstarter.data;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface DataManagerType
{
    /**
     * IMPORTANT !
     * ALL METHODS INSIDE DATAMANAGER MUST BE DEFINED HERE.
     * GROUP THE METHODS BASED ON THE MODULE.
     */

    void login (String id, String password);
    void logout ();
    void forgotPassword (String id);

    void getUserList ();
    void getUser (Integer id);
    String getUserToken();
}
