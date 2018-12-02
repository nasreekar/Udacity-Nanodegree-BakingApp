package com.example.abhijithsreekar.bakersinn;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abhijithsreekar.bakersinn.Utils.RecipeUtils;
import com.example.abhijithsreekar.bakersinn.adapters.RecipeAdapter;
import com.example.abhijithsreekar.bakersinn.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (RecipeUtils.isInLandscape(this) || RecipeUtils.isOnTablet(this)) {
            RecipeUtils.setupRecyclerView(this, binding.rvRecipeCard, 0);
        } else {
            RecipeUtils.setupRecyclerView(this, binding.rvRecipeCard, 1);
        }

        RecipeAdapter adapter = new RecipeAdapter(this, RecipeUtils.getRecipes(this));
        binding.rvRecipeCard.setAdapter(adapter);
    }
}
