package com.jmucientes.udacity.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.data.network.DataService;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private String TAG = RecipeRepository.class.getName();

    private final DataService mDataService;

    private final RecipeCache mRecipeCache;

    @Inject
    public RecipeRepository(DataService dataService, RecipeCache recipeCache) {
        mDataService = dataService;
        mRecipeCache = recipeCache;
    }

    public LiveData<List<Recipe>> getRecipes() {
        List<Recipe> cachedRecipeList = mRecipeCache.getRecipes();
        if (cachedRecipeList != null && cachedRecipeList.size() > 0) {
            MutableLiveData<List<Recipe>> cachedRecipesLiveData = new MutableLiveData<>();
            cachedRecipesLiveData.setValue(cachedRecipeList);
            Log.d(TAG, "LiveDate: Got recipes from local cache ");
            return cachedRecipesLiveData;
        }
        return fetchAllRecipesFromNetwork();
    }

    public Maybe<List<Recipe>> getRecipesMaybe() {
        Log.d(TAG, "Get recipes observable. ");
        return Maybe.concat(gerRecipesFromCache(), fetchAllRecipesFromNetworkObservable().toMaybe()).firstElement();
    }

    private Maybe<List<Recipe>> gerRecipesFromCache() {
        //return Maybe.just(mRecipeCache.getRecipes());
        Maybe<List<Recipe>> cachedRecipesMaybe;
        if (mRecipeCache.getRecipes().size() > 0){
            cachedRecipesMaybe = Maybe.just(mRecipeCache.getRecipes());
        } else {
            cachedRecipesMaybe = Maybe.empty();
        }
        return cachedRecipesMaybe;
    }

    private LiveData<List<Recipe>> fetchAllRecipesFromNetwork() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        Log.d(TAG, "ViewModel: Requesting recipes list from Network.");
        mDataService.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.d(TAG, "Got successful response from server. Code: " + response.code());
                data.setValue(response.body());
                mRecipeCache.saveRecipes(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "Network request failed. Error: " + t.getMessage());
                data.setValue(null);
            }
        });
        return data;

    }

    public Single<List<Recipe>> fetchAllRecipesFromNetworkObservable() {
        Log.d(TAG, "Requesting recipes list from Network with Rx.");
        return mDataService
                .getAllRecipesRx()
                .doOnSuccess(mRecipeCache::saveRecipes);
    }

    public LiveData<List<Recipe>> refreshData() {
        return fetchAllRecipesFromNetwork();
    }
}
