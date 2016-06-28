package com.driff.android.facebookrecipes.recipemain.di;

import com.driff.android.facebookrecipes.api.RecipeClient;
import com.driff.android.facebookrecipes.api.RecipeService;
import com.driff.android.facebookrecipes.libs.base.EventBus;
import com.driff.android.facebookrecipes.recipemain.GetNextRecipeInteractor;
import com.driff.android.facebookrecipes.recipemain.GetNextRecipeInteractorImpl;
import com.driff.android.facebookrecipes.recipemain.RecipeMainPresenter;
import com.driff.android.facebookrecipes.recipemain.RecipeMainPresenterImpl;
import com.driff.android.facebookrecipes.recipemain.RecipeMainRepository;
import com.driff.android.facebookrecipes.recipemain.RecipeMainRepositoryImpl;
import com.driff.android.facebookrecipes.recipemain.SaveRecipeInteractor;
import com.driff.android.facebookrecipes.recipemain.SaveRecipeInteractorImpl;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 23/6/2016.
 */
@Module
public class RecipeMainModule {

    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeMainView providesRecipeMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(EventBus eventBus, RecipeMainView view, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor){
        return new RecipeMainPresenterImpl(eventBus, view, saveInteractor, getNextInteractor);
    }
    @Provides
    @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides
    @Singleton
    RecipeMainRepository providesRecipeMainRepository(EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides
    @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }

}
