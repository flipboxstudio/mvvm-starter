package id.co.flipbox.mvvmstarter.data.remote;

import id.co.flipbox.mvvmstarter.MyApplication;
import id.co.flipbox.mvvmstarter.data.events.BaseEvent;
import id.co.flipbox.mvvmstarter.data.events.EmptyEvent;
import id.co.flipbox.mvvmstarter.data.events.ErrorEvent;
import id.co.flipbox.mvvmstarter.utils.S;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Response;

/**
 * Created by bukhoriaqid on 11/12/16.
 */

abstract class BaseAPI
{
    MyApplication app = MyApplication.getInstance();
    EventBus event = EventBus.getDefault();

    EmptyEvent mEmptyEvent = new EmptyEvent();

    void handleResponse (Response response, BaseEvent eventClass)
    {
        if (response.isSuccessful())
        {
            if (response.code() == S.HTTP_NO_CONTENT)
            {
                event.post(mEmptyEvent);
            }
            else
            {
                event.post(eventClass);
            }
        }
        else
        {
            event.post(new ErrorEvent(response));
        }
    }
}
