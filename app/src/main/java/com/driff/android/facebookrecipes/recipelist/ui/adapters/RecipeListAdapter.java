package com.driff.android.facebookrecipes.recipelist.ui.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.driff.android.facebookrecipes.R;
import com.driff.android.facebookrecipes.entities.Recipe;
import com.driff.android.facebookrecipes.libs.base.ImageLoader;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by johnj on 27/6/2016.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private List<Recipe> recipeList;
    private ImageLoader imageLoader;
    private OnItemClickListener onItemClickListener;

    public RecipeListAdapter(List<Recipe> recipeList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        Log.i(this.getClass().getSimpleName(), "Adapter Constructor");
        this.recipeList = recipeList;
        Log.i(this.getClass().getSimpleName(), "setting recipeList from Constructor");
        this.imageLoader = imageLoader;
        Log.i(this.getClass().getSimpleName(), "setting imageLoader from Constructor");
        this.onItemClickListener = onItemClickListener;
        Log.i(this.getClass().getSimpleName(), "setting clickListener from Constructor");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(this.getClass().getSimpleName(), "Creating VH");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_stored_recipes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipeList.get(position);
        Log.i(this.getClass().getSimpleName()+".onBindVewHolder(vh, i)", "Vinding View!");
        imageLoader.load(holder.imgRecipe, currentRecipe.getImageURL());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.imgFav.setTag(currentRecipe.isFavorite());
        if(currentRecipe.isFavorite() == true){
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            holder.imgFav.setImageResource(android.R.drawable.btn_star_big_off);
        }
        holder.setOnItemClickListener(currentRecipe, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        Log.i(this.getClass().getCanonicalName(),"setting Recipes!");
        this.recipeList = recipes;
        Log.i(this.getClass().getCanonicalName(),"List Size: "+recipes.size());
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe) {
        recipeList.remove(recipe);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgRecipe)
        ImageView imgRecipe;
        @Bind(R.id.txtRecipeName)
        TextView txtRecipeName;
        @Bind(R.id.imgFav)
        ImageButton imgFav;
        @Bind(R.id.imgDelete)
        ImageButton imgDelete;
        @Bind(R.id.fbShared)
        ShareButton fbShared;
        @Bind(R.id.fbSend)
        SendButton fbSend;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.i(this.getClass().getCanonicalName(), "VH Constructor");
            this.view= itemView;
            ButterKnife.bind(this, view);
        }

        public void setOnItemClickListener(final Recipe currentRecipe, final OnItemClickListener onItemClickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(currentRecipe);
                }
            });

            imgFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onFavClick(currentRecipe);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onDeleteClick(currentRecipe);
                }
            });
            ShareLinkContent content = new ShareLinkContent.Builder().setContentUrl(Uri.parse(currentRecipe.getSourceURL())).build();
            fbShared.setShareContent(content);
            fbSend.setShareContent(content);
        }
    }

}
