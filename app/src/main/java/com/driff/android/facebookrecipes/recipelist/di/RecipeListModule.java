package com.driff.android.facebookrecipes.recipelist.di;

import com.driff.android.facebookrecipes.api.RecipeClient;
import com.driff.android.facebookrecipes.api.RecipeService;
import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.libs.base.ImageLoader;
import com.driff.android.facebookrecipes.recipelist.RecipeListInteractor;
import com.driff.android.facebookrecipes.recipelist.RecipeListInteractorImpl;
import com.driff.android.facebookrecipes.recipelist.RecipeListPresenter;
import com.driff.android.facebookrecipes.recipelist.RecipeListPresenterImpl;
import com.driff.android.facebookrecipes.recipelist.RecipeListRepository;
import com.driff.android.facebookrecipes.recipelist.RecipeListRepositoryImpl;
import com.driff.android.facebookrecipes.recipelist.StoredRecipesInteractor;
import com.driff.android.facebookrecipes.recipelist.StoredRecipesInteractorImpl;
import com.driff.android.facebookrecipes.recipelist.ui.RecipeListView;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.RecipeListAdapter;
import com.driff.android.facebookrecipes.recipemain.GetNextRecipeInteractor;
import com.driff.android.facebookrecipes.recipemain.GetNextRecipeInteractorImpl;
import com.driff.android.facebookrecipes.recipemain.RecipeMainPresenter;
import com.driff.android.facebookrecipes.recipemain.RecipeMainPresenterImpl;
import com.driff.android.facebookrecipes.recipemain.RecipeMainRepository;
import com.driff.android.facebookrecipes.recipemain.RecipeMainRepositoryImpl;
import com.driff.android.facebookrecipes.recipemain.SaveRecipeInteractor;
import com.driff.android.facebookrecipes.recipemain.SaveRecipeInteractorImpl;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 23/6/2016.
 */
@Module
public class RecipeListModule {

    RecipeListView view;
    OnItemClickListener clickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    RecipeListView providesRecipeListView(){
        return this.view;
    }

    @Provides
    @Singleton
    RecipeListPresenter providesRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor interactor, StoredRecipesInteractor storedInteractor){
        return new RecipeListPresenterImpl(eventBus, view, interactor, storedInteractor);
    }
    @Provides
    @Singleton
    StoredRecipesInteractor providesStoredRecipesInteractor(RecipeListRepository repository){
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListInteractor providesRecipeListInteractor(RecipeListRepository repository){
        return new RecipeListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeListRepository providesRecipeListRepository(EventBus eventBus){
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides
    @Singleton
    RecipeListAdapter providesRecipeListAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener){
        return new RecipeListAdapter(recipeList, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Recipe> providesEmptyList(){
        return new ArrayList<Recipe>();
    }

}
