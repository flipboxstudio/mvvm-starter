package id.co.flipbox.mvvmstarter.viewmodels;

import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.viewmodels.inputs.UserViewModelInputs;
import id.co.flipbox.mvvmstarter.viewmodels.outputs.UserViewModelOutputs;

/**
 * Created by bukhoriaqid on 11/10/16.
 */

public class UserViewModel extends BaseViewModel implements UserViewModelInputs, UserViewModelOutputs
{
    private User mUser;

    public UserViewModel (User user)
    {
        mUser = user;
        notifyChange();
    }

    @Override
    public void setUser (User user)
    {
        mUser = user;
        notifyChange();
    }

    @Override
    public String getId ()
    {
        return String.valueOf(mUser.getId());
    }

    @Override
    public String getName ()
    {
        return String.valueOf(mUser.getName());
    }

}
