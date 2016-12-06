package id.co.flipbox.mvvmstarter.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class User
{
    @SerializedName ("id")
    public int    id;
    @SerializedName ("name")
    public String name;

    public User (int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }
}
