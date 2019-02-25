package com.jmucientes.udacity.bakingapp.home.view;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.BakingApp;
import com.jmucientes.udacity.bakingapp.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * This {@ViewModel} will hold data to be shown in the card list view on the home view.
 */
@ApplicationScope
public class RecipeViewModel extends AndroidViewModel {

    //TODO Integrate ViewModel with Mobius
    private static final String TAG = RecipeViewModel.class.getName();
    private final MutableLiveData<List<Recipe>> mRecipeList;
    private final RecipeRepository mRecipeRepository;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    public RecipeViewModel(@NonNull BakingApp application, @NonNull RecipeRepository recipeRepository) {
        super(application);
        mRecipeRepository = recipeRepository;
        
        Log.d(TAG, "Instantiated RecipeViewModel. Fetching movie list");
        mRecipeList = new MutableLiveData<>();
        fethRecipesAndUpdateLiveData();
    }

    private void fethRecipesAndUpdateLiveData() {
        disposables.add(
                mRecipeRepository.getRecipesMaybe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mRecipeList::setValue, err -> Log.e(TAG, err.getMessage()))
        );
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return mRecipeList;
    }

    public LiveData<List<Recipe>> refreshData() {
        return null;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
