package com.example.abhijithsreekar.bakersinn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abhijithsreekar.bakersinn.models.RecipeIngredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {


    private List<RecipeIngredient> ingredientsModelList;

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        holder.ingredientTitle.setText(ingredientsModelList.get(position).getIngredient());
        holder.ingredientQuantity.setText(new StringBuilder(String.valueOf(ingredientsModelList.get(position).getQuantity()))
                .append(" ")
                .append(ingredientsModelList.get(position).getMeasure()));
    }

    @Override
    public int getItemCount() {
        if (ingredientsModelList == null) return 0;
        return ingredientsModelList.size();
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1)
        TextView ingredientTitle;
        @BindView(android.R.id.text2)
        TextView ingredientQuantity;

        IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setIngredientsModelList(List<RecipeIngredient> ingredientsModelList) {
        this.ingredientsModelList = ingredientsModelList;
    }
}