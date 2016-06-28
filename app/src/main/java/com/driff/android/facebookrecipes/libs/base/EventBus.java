package com.driff.android.facebookrecipes.libs.base;

/**
 * Created by johnj on 22/6/2016.
 */
public interface EventBus {
    void Register(Object subscriber);
    void Unregister(Object subscriber);
    void post(Object event);
}
