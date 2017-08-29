package id.co.flipbox.mvvmstarter.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by bukhoriaqid on 7/28/17.
 * a simple Event Bus replacement
 * based on https://piercezaifman.com/how-to-make-an-event-bus-with-rxjava-and-rxandroid/
 * prone to memory leak. improve it when necessary
 */

public class RxBus
{
    private static final PublishSubject<Object> bus = PublishSubject.create();

    public static void send (@NonNull Object o)
    {
        bus.onNext(o);
    }

    //when activity destroyed, disposable is also removed
    public static Disposable subscribe (@NonNull Consumer<Object> action)
    {
        return bus.subscribe(action);
    }

    public static boolean hasObservers ()
    {
        return bus.hasObservers();
    }

}
