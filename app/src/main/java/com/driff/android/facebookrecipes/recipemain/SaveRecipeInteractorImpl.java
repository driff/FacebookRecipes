package com.driff.android.facebookrecipes.recipemain;

import com.driff.android.facebookrecipes.entities.Recipe;

/**
 * Created by johnj on 23/6/2016.
 */
public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {
    RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
