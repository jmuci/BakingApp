package com.jmucientes.udacity.bakingapp.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

class RecipeCache {

    private List<Recipe> mRecipeList;
    private long mLastCacheUpdate;
    private static final long CACHE_TTL = TimeUnit.MINUTES.toMillis(5);

    @Inject
    public RecipeCache() {
    }

    @NonNull
    public List<Recipe> getRecipes() {
        if (!isCacheExpired() && mRecipeList != null && mRecipeList.size() > 0) {
            Log.d("RecipeCache", "Got recipes from Cache");
            return mRecipeList;
        }
        Log.d("RecipeCache", " EMPTY cache");
        return Collections.emptyList();
    }

    public void saveRecipes(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        mLastCacheUpdate = System.currentTimeMillis();
    }

    private boolean isCacheExpired() {
        return System.currentTimeMillis() > (mLastCacheUpdate + CACHE_TTL);
    }
}
