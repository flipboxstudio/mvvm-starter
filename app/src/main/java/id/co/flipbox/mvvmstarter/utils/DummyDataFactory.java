package id.co.flipbox.mvvmstarter.utils;

import java.util.ArrayList;
import java.util.List;

import id.co.flipbox.mvvmstarter.models.User;

/**
 * Created by bukhoriaqid on 11/27/16.
 */

public class DummyDataFactory
{
    public static List<User> createDummyUsers ()
    {
        List<User> lUser = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            lUser.add(new User(i, String.format("nama user %d", i), String.format("email%d@email.com", i)));
        }
        return lUser;
    }
}
