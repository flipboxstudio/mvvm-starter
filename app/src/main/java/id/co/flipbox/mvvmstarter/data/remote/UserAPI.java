package id.co.flipbox.mvvmstarter.data.remote;

import java.util.List;

import id.co.flipbox.mvvmstarter.data.remote.contracts.CRUDContract;
import id.co.flipbox.mvvmstarter.models.User;
import id.co.flipbox.mvvmstarter.utils.constants.K;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class UserAPI extends BaseAPI implements CRUDContract<User, Integer>
{
    @Override
    public Maybe<List<User>> getList ()
    {
        return app.mAPIService.getUsers().retry(K.MAX_RETRIES).subscribeOn(Schedulers.io());
    }

    @Override
    public void create (User obj)
    {
        // TODO: implement your own code 
    }

    @Override
    public Maybe<User> read (Integer id)
    {
        return app.mAPIService.getUser(id.toString()).retry(K.MAX_RETRIES).subscribeOn(Schedulers.io());

    }

    @Override
    public void update (User obj, Integer id)
    {
        // TODO: implement your own code 
    }

    @Override
    public void delete (Integer id)
    {
        // TODO: implement your own code
    }
}
