package com.driff.android.facebookrecipes.recipelist;

import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.recipelist.events.RecipeListEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.Arrays;
import java.util.List;

/**
 * Created by johnj on 27/6/2016.
 */
public class RecipeListRepositoryImpl implements RecipeListRepository{

    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, new Select().from(Recipe.class));
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    private void post(int type, List<Recipe> recipeList){
        RecipeListEvent event = new RecipeListEvent();
        event.setType(type);
        event.setRecipes(recipeList);
        eventBus.post(event);
    }
    private void post(){
        post(RecipeListEvent.UPDATE_EVENT, null);
    }

}
