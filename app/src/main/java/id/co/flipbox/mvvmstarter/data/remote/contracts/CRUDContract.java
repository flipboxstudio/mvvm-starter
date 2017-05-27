package id.co.flipbox.mvvmstarter.data.remote.contracts;

/**
 * Created by bukhoriaqid on 5/27/17.
 */

public interface CRUDContract<T, U>
{
    void getList();

    void create(T obj);
    void read(U id);
    void update(T obj, U id);
    void delete(U id);
}
