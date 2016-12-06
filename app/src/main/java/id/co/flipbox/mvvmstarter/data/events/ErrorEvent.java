package id.co.flipbox.mvvmstarter.data.events;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import id.co.flipbox.mvvmstarter.utils.constants.I;
import id.co.flipbox.mvvmstarter.utils.constants.S;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by bukhoriaqid on 11/11/16.
 */

public class ErrorEvent extends BaseEvent
{
    String message;

    public ErrorEvent (Response response)
    {
        if (response.code() == I.HTTP_NO_CONTENT)
        {
            message = S.error_tidak_ada_data;
        }
        else if (response.body() instanceof JsonObject) // just in case ?
        {
            JsonObject tempJsonObject = (JsonObject) response.body();
            message = getErrorMessage(tempJsonObject);
        }
        else
        {
            try
            {
                message = deserializeError(response);
            }
            catch (IOException e)
            {
                message = S.error_unknown;
                e.printStackTrace();
            }
        }
    }

    public ErrorEvent (Throwable t)
    {
        message = S.error_unknown;
        if (t.getMessage() != null)
        {
            if (t.getMessage().contains("connect"))
            {
                message = S.error_connect;
            }
            else if (t.getMessage().contains("204"))
            {
                message = S.error_tidak_ada_data;
            }
            else
            {
                message = t.getMessage();
            }
        }
    }

    public String deserializeError (Response response) throws IOException
    {
        JsonObject errorResponse;

        ResponseBody            body    = response.errorBody();
        Gson                    gson    = new Gson();
        TypeAdapter<JsonObject> adapter = gson.getAdapter(JsonObject.class);
        errorResponse = adapter.fromJson(body.string());

        return getErrorMessage(errorResponse);
    }

    public String getErrorMessage (JsonObject jsonObject)
    {
        String lMessage;
        try
        { // deserialize error as JsonArray
            lMessage = jsonObject.get("message").getAsString();
            JsonArray errors = jsonObject.get("data").getAsJsonObject().get("errors").getAsJsonArray();
            if (errors != null)
            {
                lMessage += " : ";
                for (int i = 0; i < errors.size(); i++)
                {
                    lMessage += errors.get(i).getAsString();
                }
            }
        }
        catch (Exception e)
        {
            lMessage = S.error_unknown;
            e.printStackTrace();
        }
        return lMessage;
    }

    public String getMessage ()
    {
        return message;
    }
}
