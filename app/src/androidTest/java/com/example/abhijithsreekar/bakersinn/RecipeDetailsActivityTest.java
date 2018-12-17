package com.example.abhijithsreekar.bakersinn;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.abhijithsreekar.bakersinn.activity.RecipeDetailsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailsActivity> mActivityTestRule = new ActivityTestRule<>(RecipeDetailsActivity.class);

    @Before
    public void init() {
        mActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void clickRecyclerViewItem_RecipeIntroduction_OpensStepDetailActivity() {
        onView(withId(R.id.stepsFragmentContainer)).check(matches((isDisplayed())));
    }
}
