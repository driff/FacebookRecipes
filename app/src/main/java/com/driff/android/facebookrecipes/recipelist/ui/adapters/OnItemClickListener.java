package com.driff.android.facebookrecipes.recipelist.ui.adapters;

import com.driff.android.facebookrecipes.entities.Recipe;

/**
 * Created by johnj on 27/6/2016.
 */
public interface OnItemClickListener {

    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);

}
