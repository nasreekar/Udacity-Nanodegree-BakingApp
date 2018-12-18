package com.example.abhijithsreekar.bakersinn.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.models.Recipe;
import com.example.abhijithsreekar.bakersinn.models.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.utils.Constants;
import com.google.gson.Gson;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakersInnAppWidget extends AppWidgetProvider {

    SharedPreferences sharedPreferences;

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, String recipeName, List<RecipeIngredient> ingredientList) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.recipe_name, recipeName);
        views.removeAllViews(R.id.container_ingredients);
        for (RecipeIngredient ingredient : ingredientList) {
            RemoteViews ingredientView = new RemoteViews(context.getPackageName(),
                    android.R.layout.activity_list_item);
            ingredientView.setTextViewText(android.R.id.text1,
                    ingredient.getIngredient() + " (" + ingredient.getQuantity() + " " + ingredient.getMeasure() + ")");
            views.addView(R.id.container_ingredients, ingredientView);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(Constants.WIDGET_RECIPE, null);
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(result, Recipe.class);
        String recipeName = recipe.getName();
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, recipeName, recipe.getIngredients());
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}
