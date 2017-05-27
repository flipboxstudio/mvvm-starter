package id.co.flipbox.mvvmstarter.data.remote;

import java.util.List;

import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.GetUserListSuccessEvent;
import id.co.flipbox.mvvmstarter.data.events.GetUserSuccessEvent;
import id.co.flipbox.mvvmstarter.data.remote.contracts.CRUDContract;
import id.co.flipbox.mvvmstarter.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public class UserAPI extends BaseAPI implements CRUDContract<User, Integer>
{
    @Override
    public void getList ()
    {
        app.mAPIService.getUsers().enqueue(new Callback<List<User>>()
        {
            @Override
            public void onResponse (Call<List<User>> call, Response<List<User>> response)
            {
                handleResponse(response, new GetUserListSuccessEvent());
            }

            @Override
            public void onFailure (Call<List<User>> call, Throwable t)
            {
                event.post(new ErrorEvent(t));
            }
        });
    }

    @Override
    public void create (User obj)
    {
        // TODO: 5/27/17 implement your own code 
    }

    @Override
    public void read (Integer id)
    {
        app.mAPIService.getUser(id.toString()).enqueue(new Callback<User>()
        {
            @Override
            public void onResponse (Call<User> call, Response<User> response)
            {
                handleResponse(response, new GetUserSuccessEvent());
            }

            @Override
            public void onFailure (Call<User> call, Throwable t)
            {
                event.post(new ErrorEvent(t));
            }
        });
    }

    @Override
    public void update (User obj, Integer id)
    {
        // TODO: 5/27/17 implement your own code 
    }

    @Override
    public void delete (Integer id)
    {
        // TODO: 5/27/17 implement your own code
    }
}
