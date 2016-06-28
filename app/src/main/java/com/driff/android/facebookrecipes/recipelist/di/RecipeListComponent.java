package com.driff.android.facebookrecipes.recipelist.di;

import com.driff.android.facebookrecipes.libs.di.LibsModule;
import com.driff.android.facebookrecipes.recipelist.RecipeListPresenter;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.RecipeListAdapter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 23/6/2016.
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    //void inject(RecipeListActivity activity);
    RecipeListPresenter getPresenter();
    RecipeListAdapter getAdapter();
}