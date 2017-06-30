package id.co.flipbox.mvvmstarter.data.remote.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import id.co.flipbox.mvvmstarter.models.User;
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

    /*
    * below are dummy URLs. Please change it into your API endpoints
    * TODO: replace below URLs with your own
    */

    @GET ("users/{id}")
    public Call<JsonObject> login (@Path ("id") String id);

    @GET ("users/{id}")
    public Call<JsonObject> forgotPassword (@Path ("id") String id);

    @GET ("users/")
    public Call<List<User>> getUsers ();

    @GET ("users/{id}")
    public Call<User> getUser (@Path ("id") String id);

    /* end dummy URLs */

}
