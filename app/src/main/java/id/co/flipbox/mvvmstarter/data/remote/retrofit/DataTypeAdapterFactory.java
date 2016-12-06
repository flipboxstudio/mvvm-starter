package id.co.flipbox.mvvmstarter.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by bukhoriaqid on 11/12/16. Used to filter json return and only use necessary ones.
 */

public class DataTypeAdapterFactory implements TypeAdapterFactory
{
    @Override
    public <T> TypeAdapter<T> create (Gson gson, TypeToken<T> type)
    {
        final TypeAdapter<T>           lDelegate       = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> lElementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>()
        {
            @Override
            public void write (JsonWriter out, T value) throws IOException
            {
                lDelegate.write(out, value);
            }

            @Override
            public T read (JsonReader in) throws IOException
            {
                JsonElement lElement = lElementAdapter.read(in);
                if (lElement.isJsonObject())
                {
                    JsonObject lObject = lElement.getAsJsonObject();
                    if (lObject.has("data"))
                    {
                        lElement = lObject.get("data");
                    }
                }
                return lDelegate.fromJsonTree(lElement);
            }
        };
    }
}
