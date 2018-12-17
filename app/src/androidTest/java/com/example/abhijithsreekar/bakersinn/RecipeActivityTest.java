package com.example.abhijithsreekar.bakersinn;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import com.example.abhijithsreekar.bakersinn.activity.RecipeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withResourceName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityTest  {

    private static final String BROWNIES = "Brownies";
    private static final String CHEESECAKE = "Cheesecake";

    @Rule
    public android.support.test.rule.ActivityTestRule<RecipeActivity> mActivityTestRule = new android.support.test.rule.ActivityTestRule<>(RecipeActivity.class);

    @Test
    public void clickRecyclerViewItem_Brownies_OpensRecipeDetailActivity() {
        onView(ViewMatchers.withId(R.id.rv_recipeCard)).perform(RecyclerViewActions.actionOnItemAtPosition(1, scrollTo()));
        onView(withText(BROWNIES)).perform(click());
        onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar"))))
                .check(matches(withText(BROWNIES)));
    }

    @Test
    public void clickRecyclerViewItem_Cheesecake_OpensRecipeDetailActivity() {
        onView(ViewMatchers.withId(R.id.rv_recipeCard)).perform(RecyclerViewActions.actionOnItemAtPosition(3, scrollTo()));
        onView(withText(CHEESECAKE)).perform(click());
        onView(allOf(instanceOf(TextView.class), withParent(withResourceName("action_bar"))))
                .check(matches(withText(CHEESECAKE)));
    }
}
