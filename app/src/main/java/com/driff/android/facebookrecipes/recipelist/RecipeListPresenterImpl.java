package com.driff.android.facebookrecipes.recipelist;

import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.recipelist.events.RecipeListEvent;
import com.driff.android.facebookrecipes.recipelist.ui.RecipeListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by johnj on 27/6/2016.
 */
public class RecipeListPresenterImpl implements RecipeListPresenter {

    private EventBus eventBus;
    private RecipeListView view;
    private RecipeListInteractor interactor;
    private StoredRecipesInteractor storedInteractor;

    public RecipeListPresenterImpl(EventBus eventBus, RecipeListView view, RecipeListInteractor interactor, StoredRecipesInteractor storedInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
        this.storedInteractor = storedInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.Register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.Unregister(this);
        view = null;
    }

    @Override
    public void getRecipes() {
        interactor.execute();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        storedInteractor.executeDelete(recipe);
    }

    @Override
    public void toggleFavorite(Recipe recipe) {
        boolean fav = recipe.isFavorite();
        recipe.setFavorite(!fav);
        storedInteractor.executeUpdate(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeListEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case RecipeListEvent.READ_EVENT :
                    view.setRecipes(event.getRecipes());
                    break;
                case RecipeListEvent.DELETE_EVENT:
                    Recipe recipe = event.getRecipes().get(0);
                    view.recipeDeleted(recipe);
                    break;
                case RecipeListEvent.UPDATE_EVENT:
                    view.recipeUpdated();
                    break;
            }
        }
    }

    @Override
    public RecipeListView getView() {
        return this.view;
    }
}
