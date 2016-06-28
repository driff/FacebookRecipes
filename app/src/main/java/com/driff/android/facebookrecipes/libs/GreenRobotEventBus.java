package com.driff.android.facebookrecipes.libs;


import com.driff.android.facebookrecipes.libs.base.EventBus;

/**
 * Created by johnj on 22/6/2016.
 */
public class GreenRobotEventBus implements EventBus{

    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void Register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void Unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
