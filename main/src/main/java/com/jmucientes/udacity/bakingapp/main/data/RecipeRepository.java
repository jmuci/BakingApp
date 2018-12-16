package com.jmucientes.udacity.bakingapp.main.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.main.data.network.DataService;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository {

    private String TAG = RecipeRepository.class.getName();

    private final DataService mDataService;

    @Inject
    public RecipeRepository(DataService dataService) {
        mDataService = dataService;
    }

    public LiveData<List<Recipe>> getRecipes() {
        Log.d(TAG, "Get recipes. ");
        //TODO Implement caching
        return fetchAllRecipesFromNetwork();
    }

    private LiveData<List<Recipe>> fetchAllRecipesFromNetwork() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        mDataService.getAllRecipes().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.i(TAG, "Got successful response from server. Code: " + response.code());
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "Network request failed. Error: " + t.getMessage());
                //TODO Show message in the UI
                data.setValue(null);
            }
        });
        return data;

    }
}
