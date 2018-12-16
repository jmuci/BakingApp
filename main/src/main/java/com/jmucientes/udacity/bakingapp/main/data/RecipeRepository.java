package com.jmucientes.udacity.bakingapp.main.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.List;

import javax.inject.Inject;

public class RecipeRepository {

    @Inject
    public RecipeRepository() {
    }

    public LiveData<List<Recipe>> getRecipes() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();

        // TODO Replace with a network call or cache.
        String jsonResponse = Network.requestRecipeJson();
        //List<Recipe> recipeList = new Gson().fromJson(jsonResponse, Recipe.class);
        List<Recipe> recipeList = new Gson().fromJson(jsonResponse,  new TypeToken<List<Recipe>>(){}.getType());

        data.setValue(recipeList);
        return data;
    }

}
