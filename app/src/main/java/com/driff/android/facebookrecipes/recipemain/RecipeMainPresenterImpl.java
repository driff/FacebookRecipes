package com.driff.android.facebookrecipes.recipemain;

import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.events.RecipeMainEvent;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by johnj on 23/6/2016.
 */
public class RecipeMainPresenterImpl implements RecipeMainPresenter {

    private EventBus eventBus;
    RecipeMainView view;
    SaveRecipeInteractor saveInteractor;
    GetNextRecipeInteractor getNextInteractor;

    public RecipeMainPresenterImpl(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        this.eventBus = eventBus;
        this.view = view;
        this.saveInteractor = saveInteractor;
        this.getNextInteractor = getNextInteractor;
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
    public void dismissRecipe() {
        if(this.view != null){
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if(this.view != null){
            view.hideUIElements();
            view.showProgress();
        }
        getNextInteractor.execute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if(this.view != null){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveInteractor.execute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if(this.view != null){
            String error = event.getError();
            if (error != null){
                view.hideProgress();
                view.onGetRecipeError(error);
            }else{
                if(event.getType() == RecipeMainEvent.NEXT_EVENT){
                    view.setRecipe(event.getRecipe());
                }else if (event.getType() == RecipeMainEvent.SAVE_EVENT){
                    view.OnRecipeSaved();
                    getNextInteractor.execute();
                }
            }
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }

    @Override
    public void imageReady() {
        if(this.view != null){
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if(this.view != null){
            view.onGetRecipeError(error);
        }
    }
}
