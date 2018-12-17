package com.example.abhijithsreekar.bakersinn.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.activity.RecipeDetailsActivity;
import com.example.abhijithsreekar.bakersinn.models.Recipe;
import com.example.abhijithsreekar.bakersinn.models.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.utils.Constants;
import com.example.abhijithsreekar.bakersinn.utils.RecipeUtils;
import com.google.gson.Gson;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements RecyclerViewClickListener {

    private Context context;
    private List<Recipe> recipeList;
    private int[] imageIds;
    private RecyclerViewClickListener mListener;
    private SharedPreferences sharedPreferences;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
        this.mListener = this;
        this.imageIds = RecipeUtils.getRecipeImages(context);
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recipe_card_item, viewGroup, false);
        return new RecipeViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = recipeList.get(i);
        recipeViewHolder.bindRecipe(context, recipe, imageIds[i]);
    }

    @Override
    public int getItemCount() {
        return recipeList == null ? 0 : recipeList.size();
    }

    @Override
    public void onClick(View view, int position) {
        Recipe recipe = recipeList.get(position);
        List<RecipeIngredient> ingredientList = recipe.getIngredients();
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra(Constants.SELECTED_RECIPE_TO_SEE_DETAILS, recipe);
        Gson gson = new Gson();
        String ingredientJson = gson.toJson(ingredientList);
        intent.putExtra(Constants.KEY_INGREDIENTS, ingredientJson);
        String resultJson = gson.toJson(recipeList.get(position));
        sharedPreferences.edit().putString(Constants.WIDGET_RECIPE, resultJson).apply();
        context.startActivity(intent);
    }
}
