package com.jmucientes.udacity.bakingapp.data;

import android.util.Log;

import com.jmucientes.udacity.bakingapp.data.network.DataService;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class RecipeRepository {

    private String TAG = RecipeRepository.class.getName();

    private final DataService mDataService;

    private final RecipeCache mRecipeCache;

    @Inject
    public RecipeRepository(DataService dataService, RecipeCache recipeCache) {
        mDataService = dataService;
        mRecipeCache = recipeCache;
    }

    public Maybe<List<Recipe>> getRecipesMaybe() {
        Log.d(TAG, "Get recipes observable. ");
        return Maybe.concat(gerRecipesFromCache(), fetchAllRecipesFromNetworkObservable().toMaybe()).firstElement();
    }

    private Maybe<List<Recipe>> gerRecipesFromCache() {
        Maybe<List<Recipe>> cachedRecipesMaybe;
        if (mRecipeCache.getRecipes().size() > 0) {
            cachedRecipesMaybe = Maybe.just(mRecipeCache.getRecipes());
        } else {
            cachedRecipesMaybe = Maybe.empty();
        }
        return cachedRecipesMaybe;
    }

    public Single<List<Recipe>> fetchAllRecipesFromNetworkObservable() {
        Log.d(TAG, "Requesting recipes list from Network with Rx.");
        return mDataService
                .getAllRecipesRx()
                .doOnSuccess(mRecipeCache::saveRecipes);
    }
}
