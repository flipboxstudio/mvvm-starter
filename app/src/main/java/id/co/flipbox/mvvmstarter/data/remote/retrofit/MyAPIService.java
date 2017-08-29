package id.co.flipbox.mvvmstarter.data.remote.retrofit;

import com.google.gson.JsonObject;

import java.util.List;

import id.co.flipbox.mvvmstarter.models.User;
import io.reactivex.Maybe;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by bukhoriaqid on 11/12/16.
 * more read on rx / request pattern here https://medium.com/@andrew.kelly/rxjava-the-first-3-patterns-4c112a85b689
 */

public interface MyAPIService
{
    @GET
        //dynamic URL
    Maybe<JsonObject> dynamicRequest (@Url String url);

    /*
    * below are dummy URLs. Please change it into your API endpoints
    * TODO: replace below URLs with your own
    */

    @GET ("users/{id}")
    Maybe<JsonObject> login (@Path ("id") String id);

    @GET ("users/{id}")
    Maybe<JsonObject> forgotPassword (@Path ("id") String id);

    @GET ("users/")
    Maybe<List<User>> getUsers ();

    @GET ("users/{id}")
    Maybe<User> getUser (@Path ("id") String id);

    /* end dummy URLs */

}
