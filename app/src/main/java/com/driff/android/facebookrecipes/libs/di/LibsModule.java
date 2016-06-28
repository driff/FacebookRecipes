package com.driff.android.facebookrecipes.libs.di;

import android.app.Activity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.driff.android.facebookrecipes.libs.GlideImageLoader;
import com.driff.android.facebookrecipes.libs.GreenRobotEventBus;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 22/6/2016.
 */
@Module
public class LibsModule {

    private Activity activity;
    public LibsModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Activity fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Activity providesActivity(){
        return this.activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

}
