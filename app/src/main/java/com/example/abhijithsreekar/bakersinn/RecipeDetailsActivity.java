package com.example.abhijithsreekar.bakersinn;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.abhijithsreekar.bakersinn.Model.Recipe;
import com.example.abhijithsreekar.bakersinn.Model.RecipeIngredient;
import com.example.abhijithsreekar.bakersinn.Model.RecipeStep;
import com.example.abhijithsreekar.bakersinn.Utils.Constants;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity
        implements StepsFragment.OnStepItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        if (getIntent() != null) {
            if (getIntent().hasExtra(Constants.SELECTED_RECIPE_TO_SEE_DETAILS)) {
                Recipe recipe = getIntent().getParcelableExtra(Constants.SELECTED_RECIPE_TO_SEE_DETAILS);

                setTitle(recipe.getName());

                int id = recipe.getId();
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

    @Override
    public void onStepItemClicked(RecipeStep step) {
       /* Intent intent = new Intent(this, StepDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ser", stepsModel);
        intent.putExtra(Intent.EXTRA_TEXT, bundle);
        startActivity(intent);*/

        Toast.makeText(this, step.getShortDescription(), Toast.LENGTH_SHORT).show();
    }
}
