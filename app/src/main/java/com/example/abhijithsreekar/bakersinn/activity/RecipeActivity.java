package com.example.abhijithsreekar.bakersinn.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.adapters.RecipeAdapter;
import com.example.abhijithsreekar.bakersinn.api.RecipeApiService;
import com.example.abhijithsreekar.bakersinn.api.RecipeInterface;
import com.example.abhijithsreekar.bakersinn.databinding.ActivityMainBinding;
import com.example.abhijithsreekar.bakersinn.models.Recipe;
import com.example.abhijithsreekar.bakersinn.utils.NetworkUtils;
import com.example.abhijithsreekar.bakersinn.utils.RecipeUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeActivity extends AppCompatActivity {

    private static final String TAG = RecipeActivity.class.getSimpleName();
    ActivityMainBinding binding;
    RecipeInterface recipeInterface;
    List<Recipe> recipes = new ArrayList<>();
    RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recipeInterface = RecipeApiService.getRetrofitInstance().create(RecipeInterface.class);

        if (RecipeUtils.isInLandscape(this) || RecipeUtils.isOnTablet(this)) {
            RecipeUtils.setupRecyclerView(this, binding.rvRecipeCard, 0);
        } else {
            RecipeUtils.setupRecyclerView(this, binding.rvRecipeCard, 1);
        }

        List<Recipe> recipeList = getRecipesFromNetwork();

        adapter = new RecipeAdapter(this, recipeList);
        binding.rvRecipeCard.setAdapter(adapter);
    }

    private List<Recipe> getRecipesFromNetwork() {
        if (NetworkUtils.getInstance().isNetworkAvailable(this)) {
            Call<List<Recipe>> call = recipeInterface.getRecipes();
            call.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    if (response.isSuccessful()) {
                        if (!response.body().isEmpty()) {
                            Log.d(TAG, "Number of recipes retrieved: " + response.body().size());
                            adapter.addAll(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    Log.d(TAG, t.getLocalizedMessage());
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.something_wrong_text), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
        return recipes;
    }
}
