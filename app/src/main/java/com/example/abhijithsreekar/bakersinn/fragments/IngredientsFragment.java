package com.example.abhijithsreekar.bakersinn.fragments;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.adapters.IngredientsAdapter;
import com.example.abhijithsreekar.bakersinn.models.Recipe;
import com.example.abhijithsreekar.bakersinn.models.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.utils.Constants;
import com.example.abhijithsreekar.bakersinn.widget.BakersInnAppWidget;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsFragment extends Fragment {

    private List<RecipeIngredient> ingredientsList;
    @BindView(R.id.lv_ingredients)
    RecyclerView ingredientList;

    @BindView(R.id.btn_addToWidget)
    ImageView btnAddToWidget;

    Gson gson;

    public IngredientsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, rootView);

        ingredientList.setLayoutManager(new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false));
        IngredientsAdapter adapter = new IngredientsAdapter();
        ingredientList.setAdapter(adapter);
        ingredientList.setNestedScrollingEnabled(false);
        adapter.setIngredientsModelList(ingredientsList);

        btnAddToWidget.setOnClickListener(view -> addIngredientsToWidget(ingredientsList));

        return rootView;
    }

    private void addIngredientsToWidget(List<RecipeIngredient> ingredientsList) {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        Recipe recipe = gson.fromJson(sharedPreferences.getString(Constants.WIDGET_RECIPE, null), Recipe.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getActivity());
        Bundle bundle = new Bundle();
        int appWidgetId = bundle.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        BakersInnAppWidget.updateAppWidget(getActivity(), appWidgetManager, appWidgetId, recipe.getName(),
                ingredientsList);
        Toast.makeText(getActivity(), "Added " + recipe.getName() + " to Widget.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.SAVEINSTANCESTATE_INGREDIENTS_STATE, ingredientList.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(Constants.SAVEINSTANCESTATE_INGREDIENTS_STATE);
            ingredientList.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    public void setIngredientsList(List<RecipeIngredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }
}
