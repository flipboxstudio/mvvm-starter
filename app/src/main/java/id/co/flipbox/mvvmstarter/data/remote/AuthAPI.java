package id.co.flipbox.mvvmstarter.data.remote;

import com.google.gson.JsonObject;

import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.data.events.ForgotPasswordSuccessEvent;
import id.co.flipbox.mvvmstarter.data.events.LoginSuccessEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class AuthAPI extends BaseAPI
{
    public void login (String id, String password)
    {
        // TODO: 11/12/16 define your own API URL
        app.mAPIService.login(id)
                       .enqueue(new Callback<JsonObject>()
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

    public void logout ()
    {
    }

    public void forgotPassword (String id)
    {
        // TODO: 11/12/16 define your own API URL
        app.mAPIService.forgotPassword(id)
                       .enqueue(new Callback<JsonObject>()
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
