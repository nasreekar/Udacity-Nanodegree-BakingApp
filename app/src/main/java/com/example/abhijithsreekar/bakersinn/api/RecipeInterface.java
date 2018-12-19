package com.example.abhijithsreekar.bakersinn.api;

import com.example.abhijithsreekar.bakersinn.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeInterface {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}
