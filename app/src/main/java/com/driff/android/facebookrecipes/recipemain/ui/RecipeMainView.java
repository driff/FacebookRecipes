package com.driff.android.facebookrecipes.recipemain.ui;

import com.driff.android.facebookrecipes.entities.Recipe;

/**
 * Created by johnj on 23/6/2016.
 */
public interface RecipeMainView {

    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void OnRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);

}
