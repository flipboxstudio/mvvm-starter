package id.co.flipbox.mvvmstarter.data.remote.contracts;

import com.google.gson.JsonObject;

import io.reactivex.Maybe;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface Authentication
{
    Maybe<JsonObject> login (String id, String password);

    void logout ();

    Maybe<JsonObject> forgotPassword (String id);
}
