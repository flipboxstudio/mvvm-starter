package id.co.flipbox.mvvmstarter.data.remote.retrofit;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by bukhoriaqid on 11/12/16.
 */

public interface MyAPIService
{
    @GET //dynamic URL
    public Call<JsonObject> dynamicRequest (@Url String url);

    @GET ("users/{id}")
    public Call<JsonObject> login (@Path ("id") String id);

    @GET ("users/{id}")
    public Call<JsonObject> forgotPassword (@Path ("id") String id);
}
