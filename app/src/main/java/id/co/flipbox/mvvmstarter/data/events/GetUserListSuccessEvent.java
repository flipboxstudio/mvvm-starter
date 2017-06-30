package id.co.flipbox.mvvmstarter.data.events;

import java.util.List;

import id.co.flipbox.mvvmstarter.models.User;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class GetUserListSuccessEvent extends BaseEvent
{
    private List<User> mUsers;
    public GetUserListSuccessEvent (List<User> users)
    {
        mUsers = users;
    }

    public List<User> getUsers ()
    {
        return mUsers;
    }
}
