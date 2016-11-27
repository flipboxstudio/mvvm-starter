package id.co.flipbox.mvvmstarter.viewmodels;

import id.co.flipbox.mvvmstarter.models.User;

/**
 * Created by bukhoriaqid on 11/10/16.
 */

public class UserViewModel extends BaseViewModel
{
    private User mUser;

    public UserViewModel (User user)
    {
        mUser = user;
        notifyChange();
    }

    public void setUser (User user)
    {
        mUser = user;
        notifyChange();
    }

    public String getId ()
    {
        return String.valueOf(mUser.getId());
    }

    public String getName ()
    {
        return String.valueOf(mUser.getName());
    }

}
