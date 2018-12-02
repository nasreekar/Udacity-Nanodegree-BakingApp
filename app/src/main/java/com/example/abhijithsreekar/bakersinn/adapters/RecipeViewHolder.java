package com.example.abhijithsreekar.bakersinn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    void bindRecipe(Context context, Recipe recipe,int imageId) {
        recipeTitle.setText(recipe.getName());
        if (recipe.getImage() != null && !TextUtils.isEmpty(recipe.getImage())) {
            Glide.with(context)
                    .load(recipe.getImage())
                    .into(recipeImage);
        } else {
            Glide.with(context)
                    .load(imageId)
                    .into(recipeImage);
        }
    }
}
