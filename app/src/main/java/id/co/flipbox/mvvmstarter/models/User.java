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
    @SerializedName ("email")
    public String email;

    public User (int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public String getEmail ()
    {
        return email;
    }
}
