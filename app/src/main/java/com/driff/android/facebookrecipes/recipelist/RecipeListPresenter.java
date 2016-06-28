package com.driff.android.facebookrecipes.recipelist;

import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.recipelist.events.RecipeListEvent;
import com.driff.android.facebookrecipes.recipelist.ui.RecipeListView;

/**
 * Created by johnj on 27/6/2016.
 */
public interface RecipeListPresenter {

    void onCreate();
    void onDestroy();
    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);
    RecipeListView getView();
}
