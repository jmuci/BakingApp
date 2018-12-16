package com.jmucientes.udacity.bakingapp.main.home.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jmucientes.udacity.bakingapp.main.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.List;

/**
 * This {@ViewModel} will hold data to be shown in the card list view on the home view.
 */
public class HomeViewModel extends ViewModel {

    private LiveData<List<Recipe>> mRecipeList;
    private RecipeRepository mRecipeRepository;


    public HomeViewModel() {
        mRecipeRepository = new RecipeRepository();
        mRecipeList = mRecipeRepository.getRecipes();
    }

    public void init(List<Recipe> recipeList) {
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return mRecipeList;
    }
}
