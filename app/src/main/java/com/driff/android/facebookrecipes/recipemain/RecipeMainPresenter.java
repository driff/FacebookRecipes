package com.driff.android.facebookrecipes.recipemain;

import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.events.RecipeMainEvent;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by johnj on 23/6/2016.
 */
public interface RecipeMainPresenter {

    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);
    RecipeMainView getView();
    void imageReady();
    void imageError(String error);

}
