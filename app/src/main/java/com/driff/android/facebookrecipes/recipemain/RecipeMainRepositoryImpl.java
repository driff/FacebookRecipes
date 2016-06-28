package com.driff.android.facebookrecipes.recipemain;

import android.content.Context;
import android.content.pm.LauncherApps;

import com.driff.android.facebookrecipes.BuildConfig;
import com.driff.android.facebookrecipes.R;
import com.driff.android.facebookrecipes.api.RecipeSearchResponse;
import com.driff.android.facebookrecipes.api.RecipeService;
import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.events.RecipeMainEvent;
import com.driff.android.facebookrecipes.libs.base.EventBus;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by johnj on 23/6/2016.
 */
public class RecipeMainRepositoryImpl implements RecipeMainRepository {
    private int recipePage;
    private EventBus eventBus;
    private RecipeService service;

    public RecipeMainRepositoryImpl(EventBus eventBus, RecipeService service) {
        this.eventBus = eventBus;
        this.service = service;
    }

    @Override
    public void getNextRecipe() {
        Call<RecipeSearchResponse> call = service.search(BuildConfig.FOOD_API_KEY, RECENT_SORT, COUNT, recipePage);
        Callback<RecipeSearchResponse> callback = new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                if(response.isSuccessful()){
                    RecipeSearchResponse recipeSearchResponse = response.body();
                    if(recipeSearchResponse.getCount() == 0){
                        setRecipePage(new Random().nextInt(RECIPE_RANGE));
                        getNextRecipe();
                    }else{
                        Recipe recipe = recipeSearchResponse.getFirstRecipe();
                        if(recipe != null){
                            post(recipe);
                        }else{
                            post(response.message());
                        }
                    }
                }else {
                    post(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {

            }
        };
        call.enqueue(callback);
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        recipe.save();
        post();
    }

    @Override
    public void setRecipePage(int recipePage) {
        this.recipePage = recipePage;
    }

    private void post(String error, int type, Recipe recipe){
        RecipeMainEvent event = new RecipeMainEvent();
        event.setType(type);
        event.setError(error);
        event.setRecipe(recipe);
        eventBus.post(event);
    }

    private void post(String error){
        post(error, RecipeMainEvent.NEXT_EVENT, null);
    }

    private void post(Recipe recipe){
        post(null, RecipeMainEvent.NEXT_EVENT, recipe);
    }

    private void post(){
        post(null, RecipeMainEvent.SAVE_EVENT, null);
    }

}
