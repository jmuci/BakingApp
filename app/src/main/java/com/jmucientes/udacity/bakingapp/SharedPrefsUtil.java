package com.jmucientes.udacity.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jmucientes.udacity.bakingapp.model.Ingredient;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.jmucientes.udacity.bakingapp.widget.IngredientsWidgetProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefsUtil {
    private static final String INGREDIENTS_PREFS_NAME = "recipe_ingredients";
    private static final String INGR_STRING_SET = "ingredients_string_set";

    public static Set<String> fetchSavedIngredientsSet(Context activity) {
        SharedPreferences recipeIngredients = activity.getSharedPreferences(INGREDIENTS_PREFS_NAME, MODE_PRIVATE);
        return recipeIngredients.getStringSet(INGR_STRING_SET, null);
    }

    public static Boolean isSharedPrefsEmpty(Context activity) {
        SharedPreferences recipeIngredients = activity.getSharedPreferences(INGREDIENTS_PREFS_NAME, MODE_PRIVATE);
        return recipeIngredients.getStringSet(INGR_STRING_SET, null) != null;
    }

    public static void saveRecipteToSharedPreferences(Activity activityContext, Recipe currentRecipe) {
        SharedPreferences recipeIngredients = activityContext.getSharedPreferences(INGREDIENTS_PREFS_NAME, MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = recipeIngredients.edit();
        editor.putStringSet(INGR_STRING_SET, simplifiedIngredientsSet(currentRecipe.getIngredients()));
        editor.apply();
        updateWidgetContent(activityContext);
    }

    private static Set<String> simplifiedIngredientsSet(List<Ingredient> ingredients) {
        Set<String> simplifiedIngredientStringSet = new HashSet<>(ingredients.size());
        for (Ingredient ingredient: ingredients) {
            simplifiedIngredientStringSet.add(ingredient.getIngredient());
        }
        return simplifiedIngredientStringSet;
    }

    private static void updateWidgetContent(Activity activityContext) {
        Intent intent = new Intent(activityContext, IngredientsWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(activityContext.getApplication()).getAppWidgetIds(new ComponentName(activityContext.getApplication(), IngredientsWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        activityContext.sendBroadcast(intent);
    }
}
