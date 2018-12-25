package com.jmucientes.udacity.bakingapp.main.data;

import com.jmucientes.udacity.bakingapp.main.model.Recipe;

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

    public List<Recipe> getRecipes() {
        if (!isCacheExpired() && mRecipeList != null && mRecipeList.size() > 0) {
            return mRecipeList;
        }
        return null;
    }

    public void saveRecipes(List<Recipe> recipeList) {
        mRecipeList = recipeList;
        mLastCacheUpdate = System.currentTimeMillis();
    }

    private boolean isCacheExpired() {
        return System.currentTimeMillis() > (mLastCacheUpdate + CACHE_TTL);
    }
}
