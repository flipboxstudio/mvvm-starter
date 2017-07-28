package id.co.flipbox.mvvmstarter.data.remote;

import com.google.gson.JsonObject;

import org.reactivestreams.Publisher;

import id.co.flipbox.mvvmstarter.data.remote.contracts.Authentication;
import id.co.flipbox.mvvmstarter.utils.constants.K;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class AuthAPI extends BaseAPI implements Authentication
{
    @Override
    public Maybe<JsonObject> login (String id, String password)
    {
        // TODO: define your own API URL
        return app.mAPIService.login(id)
                              .retry(K.MAX_RETRIES)
                              .subscribeOn(Schedulers.io());

        // TODO: 7/28/17 find new helper for retryWhen ( rx 2.1.2 )
    }

    @Override
    public void logout ()
    {
    }

    @Override
    public Maybe<JsonObject> forgotPassword (String id)
    {
        // TODO: define your own API URL
        return app.mAPIService.forgotPassword(id)
                              .retry(K.MAX_RETRIES)
                              .subscribeOn(Schedulers.io());

        // TODO: 7/28/17 find new helper for retryWhen ( rx 2.1.2 )
    }
}
