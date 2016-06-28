package com.driff.android.facebookrecipes.recipelist.ui;

import com.driff.android.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by johnj on 27/6/2016.
 */
public interface RecipeListView{

    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);

}
