package com.example.abhijithsreekar.bakersinn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.abhijithsreekar.bakersinn.fragments.IngredientsFragment;
import com.example.abhijithsreekar.bakersinn.fragments.StepDetailFragment;
import com.example.abhijithsreekar.bakersinn.fragments.StepsFragment;
import com.example.abhijithsreekar.bakersinn.models.Recipe;
import com.example.abhijithsreekar.bakersinn.models.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.models.RecipeStep;
import com.example.abhijithsreekar.bakersinn.utils.Constants;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity
        implements StepsFragment.OnStepItemClickListener {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        mTwoPane = checkForTwoPane();

        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.SELECTED_RECIPE_TO_SEE_DETAILS)) {
                Recipe recipe = getIntent().getParcelableExtra(Constants.SELECTED_RECIPE_TO_SEE_DETAILS);

                setTitle(recipe.getName());

                List<RecipeIngredient> ingredients = recipe.getIngredients();
                List<RecipeStep> steps = recipe.getSteps();

                FragmentManager fragmentManager = getSupportFragmentManager();

                IngredientsFragment ingredientFragment = new IngredientsFragment();
                ingredientFragment.setIngredientsList(ingredients);

                if (savedInstanceState == null) {
                    fragmentManager.beginTransaction()
                            .add(R.id.ingredientsFragmentContainer, ingredientFragment)
                            .commit();
                } else {
                    fragmentManager.beginTransaction()
                            .replace(R.id.ingredientsFragmentContainer, ingredientFragment)
                            .commit();
                }

                StepsFragment stepFragment = new StepsFragment();
                stepFragment.setStepsList(steps);

                fragmentManager.beginTransaction()
                        .add(R.id.stepsFragmentContainer, stepFragment)
                        .commit();

            }
        }
    }

    private boolean checkForTwoPane() {
        return findViewById(R.id.detail_container) != null;
    }

    @Override
    public void onStepItemClicked(RecipeStep step) {
        if (mTwoPane) {
            StepDetailFragment recipeStepDetailFragment = StepDetailFragment.newInstance(step);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, recipeStepDetailFragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StepDetailActivity.class);
            intent.putExtra(Constants.SELECTED_STEP_TO_SEE_VIDEO, step);
            startActivity(intent);
        }
    }
}
