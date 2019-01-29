package com.jmucientes.udacity.bakingapp.home.mobius.effecthandlers;

import android.util.Log;

import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.spotify.mobius.rx2.RxMobius;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.NavigateToRecipeDetailsList;
import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.RequestRecipes;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class HomeEffectHandlers {

    private static final String TAG = HomeEffectHandlers.class.getName();

    private HomeEffectHandlers() {
    }

    public static ObservableTransformer<HomeEffect, HomeEvent> provideEffectHandler(RecipeRepository recipeRepository, Consumer<Recipe> navigateToDetailsCommand) {
        Log.d(TAG, "setUp -> provideEffectHandler()");
        return RxMobius.<HomeEffect, HomeEvent>subtypeEffectHandler()
                .addTransformer(RequestRecipes.class, handleRequestRecipes(recipeRepository))
                .addConsumer(NavigateToRecipeDetailsList.class, handleNavigateToRecipe(navigateToDetailsCommand), mainThread())
                .build();
    }

    private static ObservableTransformer<RequestRecipes, HomeEvent> handleRequestRecipes(
            RecipeRepository recipeRepository) {
        return requests ->
                requests.flatMap(request ->
                        recipeRepository.getRecipesMaybe()
                                .doAfterSuccess(result -> Log.d(TAG, "handleRequestRecipes() Got response from server. Size: " + result.size() ))
                                .toObservable()
                                .map(result -> HomeEvent.recipesLoaded(ImmutableList.copyOf(result))));
                                //.onErrorReturn(err -> Event.searchError(request.query())));
    }

    private static Consumer<NavigateToRecipeDetailsList> handleNavigateToRecipe(Consumer<Recipe> command) {
        return navigationEffect -> command.accept(navigationEffect.recipe());
    }

}
