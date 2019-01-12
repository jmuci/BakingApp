package com.jmucientes.udacity.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        //mActivityRule.launchActivity(MY_ACTIVITY_INTENT);
    }

    @Test
    public void checkUserCanNavigateToIngredientsView() {
        //HomeFragment fragment = new HomeFragment();
        //mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.recipes_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Navigate to Recipe Details Fragment
        onView(withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.ingredients_cardView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Navigate to Ingredient Details Fragment
        onView(withId(R.id.ingredients_cardView)).perform(click());
        onView(withId(R.id.ingredients_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void checkUserCanNavigateToAllFourRecipes() {
        //HomeFragment fragment = new HomeFragment();
        //mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.recipes_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Navigate to Recipe Details Fragment
        onView(withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ingredients_cardView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        pressBack();

        onView(withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.ingredients_cardView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        pressBack();

        onView(withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.ingredients_cardView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        pressBack();

    }

    @Test
    public void checkUserCanNavigateToStepDetailsView() {
        //HomeFragment fragment = new HomeFragment();
        //mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

        onView(withId(R.id.progress_bar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.recipes_recycler_view)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Navigate to Recipe Details Fragment
        onView(withId(R.id.recipes_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.ingredients_cardView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Navigate to Step Detail Video Fragment
        onView(withId(R.id.recipe_steps_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_description)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}