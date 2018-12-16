package com.jmucientes.udacity.bakingapp.main.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {

    public LiveData<List<Recipe>> getRecipes() {
        final MutableLiveData<List<Recipe>> data = new MutableLiveData<>();
        // TODO Replace with a network call or cache.
        data.setValue(new ArrayList<>(Arrays.asList(new Recipe(), new Recipe(), new Recipe())));
        return data;
    }

}
