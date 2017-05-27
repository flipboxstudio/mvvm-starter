package id.co.flipbox.mvvmstarter.data.local;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import id.co.flipbox.mvvmstarter.data.local.contracts.AGERContract;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.utils.constants.K;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class UserStorage implements AGERContract<User, Integer>
{
    @Override
    public List<User> getList ()
    {
        List<User> lUsers = Hawk.get(K.USER_LIST);
        return lUsers;
    }

    @Override
    public User get (Integer id)
    {
        User lUser = Hawk.get(String.format(K.USER_DETAIL, id));
        return lUser;
    }

    @Override
    public void add (User obj)
    {
        Hawk.put(String.format(K.USER_DETAIL, obj.getId()), obj);
    }

    @Override
    public void edit (User obj, Integer id)
    {
        Hawk.put(String.format(K.USER_DETAIL, id), obj);
    }

    @Override
    public void delete (Integer id)
    {
        Hawk.delete(String.format(K.USER_DETAIL, id));
    }

    public String getToken ()
    {
        return "";
    }
}
