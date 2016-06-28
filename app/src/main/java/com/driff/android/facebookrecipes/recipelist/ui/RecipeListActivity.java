package com.driff.android.facebookrecipes.recipelist.ui;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.driff.android.facebookrecipes.FacebookRecipesApp;
import com.driff.android.facebookrecipes.R;
import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.libs.GlideImageLoader;
import com.driff.android.facebookrecipes.libs.base.ImageLoader;
import com.driff.android.facebookrecipes.recipelist.RecipeListPresenter;
import com.driff.android.facebookrecipes.recipelist.di.RecipeListComponent;
import com.driff.android.facebookrecipes.recipelist.events.RecipeListEvent;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import com.driff.android.facebookrecipes.recipelist.ui.adapters.RecipeListAdapter;
import com.driff.android.facebookrecipes.recipemain.ui.RecipeMainActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private RecipeListAdapter adapter;
    private RecipeListPresenter presenter;
    private RecipeListComponent component;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        Log.i(this.getClass().getCanonicalName(), "onCreate()");
        ButterKnife.bind(this);
        Log.i(this.getClass().getCanonicalName(), "binding ButterKnife");
        setupToolbar();
        Log.i(this.getClass().getCanonicalName(), "setting Toolbar");
        setupInjection();
        Log.i(this.getClass().getCanonicalName(), "setting injection");
        setupRecyclerView();
        Log.i(this.getClass().getCanonicalName(), "setting RecyclerView");
        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipes_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.actionMain){
            navigateToMainScreen();
        }else if(id == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupInjection() {
        //Testing adapter
        /*ImageLoader loader = new GlideImageLoader(Glide.with(this));
        Recipe recipe = new Recipe();
        recipe.setFavorite(false);
        recipe.setTitle("Prueba");
        recipe.setSourceURL("http://static.food2fork.com/icedcoffee5766.jpg");
        recipe.setImageURL("http://static.food2fork.com/icedcoffee5766.jpg");
        adapter = new RecipeListAdapter(Arrays.asList(recipe),loader, this);
        presenter = new RecipeListPresenter() {
            @Override
            public void onCreate() {

            }

            @Override
            public void onDestroy() {

            }

            @Override
            public void getRecipes() {

            }

            @Override
            public void removeRecipe(Recipe recipe) {

            }

            @Override
            public void toggleFavorite(Recipe recipe) {

            }

            @Override
            public void onEventMainThread(RecipeListEvent event) {

            }

            @Override
            public RecipeListView getView() {
                return null;
            }
        };*/
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component = app.getRecipeListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
        Log.i(this.getClass().getCanonicalName(), "adapter: "+adapter.toString());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        Log.i(this.getClass().getCanonicalName(), "LayoutManager asigned to RV");
        recyclerView.setAdapter(adapter);
        Log.i(this.getClass().getCanonicalName(), "Adapter asigned to RV... "+adapter.getItemCount());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipeListAdapter getAdapter() {
        return component.getAdapter();
    }
}
