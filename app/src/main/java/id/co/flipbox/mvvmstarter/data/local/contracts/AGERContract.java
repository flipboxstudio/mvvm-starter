package id.co.flipbox.mvvmstarter.data.local.contracts;

import java.util.List;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface AGERContract<T, U>
{
    List<T> getList ();
    T get(U id);

    void add(T obj);
    void edit(T obj, U id);
    void delete(U id);
}
