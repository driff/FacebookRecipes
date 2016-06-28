package com.driff.android.facebookrecipes.events;

import com.driff.android.facebookrecipes.entities.Recipe;

/**
 * Created by johnj on 23/6/2016.
 */
public class RecipeMainEvent {

    private int type;
    private String error;
    private Recipe recipe;

    public static final int NEXT_EVENT = 0;
    public static final int SAVE_EVENT = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
