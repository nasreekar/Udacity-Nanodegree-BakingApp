package com.example.abhijithsreekar.bakersinn.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.abhijithsreekar.bakersinn.R;
import com.example.abhijithsreekar.bakersinn.fragments.StepDetailFragment;
import com.example.abhijithsreekar.bakersinn.models.RecipeStep;
import com.example.abhijithsreekar.bakersinn.utils.Constants;

public class StepDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);

        if (getIntent() != null) {
            RecipeStep recipeStep = getIntent().getParcelableExtra(Constants.SELECTED_STEP_TO_SEE_VIDEO);

            if (getSupportFragmentManager().findFragmentById(R.id.step_video_container) == null) {
                StepDetailFragment recipeStepDetailFragment = StepDetailFragment.newInstance(recipeStep);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.step_video_container, recipeStepDetailFragment)
                        .commit();
            }

            setTitle(recipeStep.getShortDescription());
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }

}
