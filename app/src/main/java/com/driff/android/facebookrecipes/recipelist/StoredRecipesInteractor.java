package com.driff.android.facebookrecipes.recipelist;

import com.driff.android.facebookrecipes.entities.Recipe;

/**
 * Created by johnj on 27/6/2016.
 */
public interface StoredRecipesInteractor {

    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);

}
