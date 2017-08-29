package id.co.flipbox.mvvmstarter.data.remote.contracts;

import java.util.List;

import id.co.flipbox.mvvmstarter.models.User;
import io.reactivex.Maybe;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface CRUDContract<T, U>
{
    Maybe<List<User>> getList ();

    void create (T obj);

    Maybe<User> read (U id);

    void update (T obj, U id);

    void delete (U id);
}
