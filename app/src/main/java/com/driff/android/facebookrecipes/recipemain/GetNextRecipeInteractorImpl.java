package com.driff.android.facebookrecipes.recipemain;

import java.util.Random;

/**
 * Created by johnj on 23/6/2016.
 */
public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {
    RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
