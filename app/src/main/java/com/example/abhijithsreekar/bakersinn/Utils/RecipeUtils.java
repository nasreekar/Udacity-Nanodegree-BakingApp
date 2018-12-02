package com.example.abhijithsreekar.bakersinn.Utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.abhijithsreekar.bakersinn.Model.Recipe;
import com.example.abhijithsreekar.bakersinn.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeUtils {

    public static List<Recipe> getRecipes(Context context) {
        List<Recipe> recipes = new ArrayList<>();
        Gson gson = new Gson();
        try {
            InputStream inputStream = context.getAssets().open("recipes.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            JsonReader reader = new JsonReader(inputStreamReader);
            Type type = new TypeToken<List<Recipe>>() {
            }.getType();
            recipes = gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }

    public static int[] getRecipeImages(Context context) {
        TypedArray typedArray = context.getResources().obtainTypedArray(R.array.recipeImages);
        int count = typedArray.length();
        int[] ids = new int[count];
        for (int i = 0; i < count; i++) {
            ids[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();

        return ids;
    }
}
