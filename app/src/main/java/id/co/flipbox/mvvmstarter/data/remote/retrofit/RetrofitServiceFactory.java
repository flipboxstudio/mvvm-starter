package id.co.flipbox.mvvmstarter.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import id.co.flipbox.mvvmstarter.data.DataManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bukhoriaqid on 11/12/16.
 */

public class RetrofitServiceFactory
{
    // TODO: define your own base url
    private static final String               BASE_URL   = "https://jsonplaceholder.typicode.com";
    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static final Gson                 gson       = new GsonBuilder()
            .registerTypeAdapterFactory(new DataTypeAdapterFactory()).create();
    private static final Retrofit.Builder     sBuilder   = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(gson));

    public static Retrofit sRetrofit;

    public static <S> S createService (Class<S> serviceClass)
    {
        final String authHeader;
        if (DataManager.can().getUserToken() != null)
        {
            authHeader = "Bearer " + DataManager.can().getUserToken();
        }
        else
        {
            authHeader = "";
        }

        httpClient.addInterceptor(new ResponseInterceptor());
        //add authorization header
        httpClient.addInterceptor(new Interceptor()
        {
            @Override
            public Response intercept (Chain chain) throws IOException
            {
                Request lOriginalRequest = chain.request();
                Request lRequest = lOriginalRequest.newBuilder().header("Authorization", authHeader)
                                                   .method(lOriginalRequest.method(), lOriginalRequest.body()).build();

                return chain.proceed(lRequest);
            }
        });

        OkHttpClient lClient = httpClient.build();
        sRetrofit = sBuilder.client(lClient).build();
        return sRetrofit.create(serviceClass);
    }

}
