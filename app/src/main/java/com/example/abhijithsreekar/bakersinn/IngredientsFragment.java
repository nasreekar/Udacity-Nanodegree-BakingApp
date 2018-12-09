package com.example.abhijithsreekar.bakersinn;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhijithsreekar.bakersinn.Model.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.adapters.IngredientsAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsFragment extends Fragment {

    private List<RecipeIngredient> ingredientsList;
    @BindView(R.id.lv_ingredients)
    RecyclerView ingredientList;

    private IngredientsAdapter adapter;


    public IngredientsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);
        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new IngredientsAdapter();
        ingredientList.setAdapter(adapter);
        ingredientList.setNestedScrollingEnabled(false);
        adapter.setIngredientsModelList(ingredientsList);
        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("state", ingredientList.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable("state");
            ingredientList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    public void setIngredientsList(List<RecipeIngredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
