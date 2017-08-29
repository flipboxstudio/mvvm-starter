package id.co.flipbox.mvvmstarter.data.local;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import id.co.flipbox.mvvmstarter.data.local.contracts.AGERContract;
import id.co.flipbox.mvvmstarter.data.local.contracts.CacheContract;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.utils.constants.K;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class UserStorage implements AGERContract<User, Integer>, CacheContract
{
    @Override
    public boolean isCacheValid ()
    {
        // TODO: 7/28/17 define your own cache validity
        return true;
    }


    @Override
    public Maybe<List<User>> getList ()
    {
        List<User> users = isCacheValid() ? Hawk.get(K.USER_LIST, new ArrayList<User>()) : null;
        return Maybe.just(users).subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<User> get (Integer id)
    {
        return Maybe.just(isCacheValid() ? Hawk.get(String.format(K.USER_DETAIL, id), new User(0, "", "")) : null);
    }

    @Override
    public void add (User obj)
    {
        Hawk.put(String.format(K.USER_DETAIL, obj.getId()), obj);
    }

    @Override
    public void addAll (List<User> objs)
    {
        Hawk.put(K.USER_LIST, objs);
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
