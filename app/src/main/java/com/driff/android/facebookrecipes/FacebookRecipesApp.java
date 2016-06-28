package com.driff.android.facebookrecipes;

import android.app.Application;
import android.content.Intent;

import com.driff.android.facebookrecipes.libs.di.LibsModule;
import com.driff.android.facebookrecipes.login.ui.LoginActivity;
import com.driff.android.facebookrecipes.recipelist.di.DaggerRecipeListComponent;
import com.driff.android.facebookrecipes.recipelist.di.RecipeListComponent;
import com.driff.android.facebookrecipes.recipelist.di.RecipeListModule;
import com.driff.android.facebookrecipes.recipelist.ui.RecipeListActivity;
import com.driff.android.facebookrecipes.recipelist.ui.RecipeListView;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.driff.android.facebookrecipes.recipemain.di.DaggerRecipeMainComponent;
import com.driff.android.facebookrecipes.recipemain.di.RecipeMainComponent;
import com.driff.android.facebookrecipes.recipemain.di.RecipeMainModule;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainView;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by johnj on 22/6/2016.
 */
public class FacebookRecipesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeMainComponent(RecipeMainActivity activity, RecipeMainView view){
        return DaggerRecipeMainComponent.builder().libsModule(new LibsModule(activity)).recipeMainModule(new RecipeMainModule(view)).build();
    }

     public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener){
        return DaggerRecipeListComponent.builder().libsModule(new LibsModule(activity)).recipeListModule(new RecipeListModule(view, clickListener)).build();
    }
}
