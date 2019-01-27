package com.jmucientes.udacity.bakingapp.home.view;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.BakingApp;
import com.jmucientes.udacity.bakingapp.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import javax.inject.Inject;

/**
 * This {@ViewModel} will hold data to be shown in the card list view on the home view.
 */
@ApplicationScope
public class RecipeViewModel extends AndroidViewModel {

    //TODO Integrate ViewModel with Mobius
    private static final String TAG = RecipeViewModel.class.getName();
    private final LiveData<List<Recipe>> mRecipeList;
    private final RecipeRepository mRecipeRepository;

    @Inject
    public RecipeViewModel(@NonNull BakingApp application, @NonNull RecipeRepository recipeRepository) {
        super(application);
        mRecipeRepository = recipeRepository;
        
        Log.d(TAG, "Instantiated RecipeViewModel. Fetching movie list");
        mRecipeList = mRecipeRepository.getRecipes();
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return mRecipeList;
    }

    public LiveData<List<Recipe>> refreshData() {
        return mRecipeRepository.refreshData();
    }
}
