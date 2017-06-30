package id.co.flipbox.mvvmstarter.data.remote;

import com.google.gson.JsonObject;

import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.ForgotPasswordSuccessEvent;
import id.co.flipbox.mvvmstarter.data.events.LoginSuccessEvent;
import id.co.flipbox.mvvmstarter.data.remote.contracts.Authentication;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class AuthAPI extends BaseAPI implements Authentication
{
    @Override
    public void login (String id, String password)
    {
        // TODO: define your own API URL
        app.mAPIService.login(id).enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse (Call<JsonObject> call, Response<JsonObject> response)
            {
                handleResponse(response, new LoginSuccessEvent());
            }

            @Override
            public void onFailure (Call<JsonObject> call, Throwable t)
            {
                event.post(new ErrorEvent(t));
            }
        });
    }

    @Override
    public void logout ()
    {
    }

    @Override
    public void forgotPassword (String id)
    {
        // TODO: define your own API URL
        app.mAPIService.forgotPassword(id).enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse (Call<JsonObject> call, Response<JsonObject> response)
            {
                handleResponse(response, new ForgotPasswordSuccessEvent());
            }

            @Override
            public void onFailure (Call<JsonObject> call, Throwable t)
            {
                event.post(new ErrorEvent(t));
            }
        });
    }
}
