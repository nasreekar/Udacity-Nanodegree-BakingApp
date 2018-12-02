package com.example.abhijithsreekar.bakersinn.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhijithsreekar.bakersinn.Model.Recipe;
import com.example.abhijithsreekar.bakersinn.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecipeViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_recipeTitle)
    TextView recipeTitle;

    @BindView(R.id.iv_recipeImage)
    ImageView recipeImage;

    RecipeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void bindRecipe(Recipe recipe) {
        recipeTitle.setText(recipe.getName());
    }
}
