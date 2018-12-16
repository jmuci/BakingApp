package com.jmucientes.udacity.bakingapp.main.home.view;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.main.BakingApp;
import com.jmucientes.udacity.bakingapp.main.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.main.di.scopes.ApplicationScope;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.List;

import javax.inject.Inject;

/**
 * This {@ViewModel} will hold data to be shown in the card list view on the home view.
 */
@ApplicationScope
public class HomeViewModel extends AndroidViewModel {

    private static final String TAG = HomeViewModel.class.getName();
    private final LiveData<List<Recipe>> mRecipeList;
    private final RecipeRepository mRecipeRepository;

    @Inject
    public HomeViewModel(@NonNull BakingApp application, @NonNull RecipeRepository recipeRepository) {
        super(application);
        mRecipeRepository = recipeRepository;
        
        Log.d(TAG, "Instantiated MovieViewModel. Fetching movie list");
        mRecipeList = mRecipeRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return mRecipeList;
    }
}
