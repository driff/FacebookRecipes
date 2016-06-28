package com.driff.android.facebookrecipes.recipemain.di;

import com.driff.android.facebookrecipes.libs.base.ImageLoader;
import com.driff.android.facebookrecipes.libs.di.LibsModule;
import com.driff.android.facebookrecipes.recipemain.RecipeMainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 23/6/2016.
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {

    /*void inject(RecipeMainActivity activity);*/
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();

}
