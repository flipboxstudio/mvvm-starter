package id.co.flipbox.mvvmstarter.data.local.contracts;

import java.util.List;

import io.reactivex.Maybe;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface RAGEContract<T, U>
{
    Maybe<List<T>> getList ();

    Maybe<T> get (U id);

    void addAll (List<T> objs);

    void add (T obj);

    void edit (T obj, U id);

    void delete (U id);
}
