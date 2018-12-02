package com.example.abhijithsreekar.bakersinn.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.abhijithsreekar.bakersinn.Model.Recipe;
import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.Utils.RecipeUtils;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;
    private int[] imageIds;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
        this.imageIds = RecipeUtils.getRecipeImages(context);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_recipe_card_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = recipeList.get(i);
        recipeViewHolder.bindRecipe(context,recipe,imageIds[i]);
    }

    @Override
    public int getItemCount() {
        return recipeList == null ? 0 : recipeList.size();
    }
}