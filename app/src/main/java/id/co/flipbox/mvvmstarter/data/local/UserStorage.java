package id.co.flipbox.mvvmstarter.data.local;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import id.co.flipbox.mvvmstarter.data.local.contracts.CacheContract;
import id.co.flipbox.mvvmstarter.data.local.contracts.RAGEContract;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.utils.constants.K;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class UserStorage implements RAGEContract<User, Integer>, CacheContract
{
    @Override
    public boolean isCacheValid ()
    {
        // TODO: 7/28/17 define your own cache validity
        return false;
    }


    @Override
    public Maybe<List<User>> getList ()
    {
        List<User> users = isCacheValid() ? Hawk.get(K.USER_LIST, new ArrayList<User>()) : null;
        return users == null ? Maybe.<List<User>>empty() : Maybe.just(users).subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<User> get (Integer id)
    {
        User user = isCacheValid() ? Hawk.get(String.format(K.USER_DETAIL, id), new User(0, "", "")) : null;
        return user == null ? Maybe.<User>empty() : Maybe.just(user).subscribeOn(Schedulers.io());
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
