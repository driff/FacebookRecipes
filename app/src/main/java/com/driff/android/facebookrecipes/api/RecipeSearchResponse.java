package com.driff.android.facebookrecipes.api;

import com.driff.android.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by johnj on 22/6/2016.
 */
public class RecipeSearchResponse {

    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Recipe getFirstRecipe(){
        Recipe first = null;
        if(!recipes.isEmpty()){
            first = recipes.get(0);
        }
        return first;
    }

}
