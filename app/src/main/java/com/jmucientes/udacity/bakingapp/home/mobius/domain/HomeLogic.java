package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import android.support.annotation.NonNull;
import android.util.Log;

import com.spotify.mobius.First;
import com.spotify.mobius.Next;

import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.navigateToRecipeDetailsList;
import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.requestRecipes;
import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.showFeedback;
import static com.spotify.mobius.Effects.effects;
import static com.spotify.mobius.First.first;

public final class HomeLogic {

    private static final String TAG = HomeLogic.class.getName();

    private HomeLogic() {}

    @NonNull
    public static First<HomeModel, HomeEffect> init(HomeModel model) {
        return first(model, effects(requestRecipes()));
    }

    @NonNull
    public static Next<HomeModel, HomeEffect> update(HomeModel model, HomeEvent event) {

        return event.map(
                recipeCardClicked -> onRecipeCardClicked(model, recipeCardClicked),
                recipesLoaded -> onRecipesLoaded(model, recipesLoaded),
                taskLoadingFailed -> onTaskLoadingFailed(model)
        );
    }

    private static Next<HomeModel, HomeEffect> onRecipeCardClicked(HomeModel model, HomeEvent.RecipeCardClicked recipeCardClickedEvent) {
        if (model.recipes() != null && !model.recipes().isEmpty()) {
            Log.d(TAG, "update().onRecipeCardClicked() -> Next.next(effect(navToRecipe)). Recipe: " + recipeCardClickedEvent.recipe());
            return Next.dispatch(effects(navigateToRecipeDetailsList(recipeCardClickedEvent.recipe())));
        } else {
            Log.d(TAG, "update().onRecipeCardClicked() -> Next.noChange(). Recipe: " + recipeCardClickedEvent.recipe());
            return Next.noChange();
        }
    }

    private static Next<HomeModel,HomeEffect> onRecipesLoaded(HomeModel model, HomeEvent.RecipesLoaded recipesLoadedEvent) {
        if (recipesLoadedEvent.recipes().isEmpty() || recipesLoadedEvent.recipes().equals(model.recipes())) {
            Log.d(TAG, "update().onRecipesLoaded() -> Next.noChange(). Recipes: " + recipesLoadedEvent.recipes());
            return Next.noChange();
        }
        Log.d(TAG, "update().onRecipesLoaded() -> Next.next(model.with). Recipes: " + recipesLoadedEvent.recipes());
        return Next.next(model.withRecipes(recipesLoadedEvent.recipes()));
    }

    private static Next<HomeModel, HomeEffect> onTaskLoadingFailed(HomeModel model) {
        return Next.dispatch(effects(showFeedback(FeedbackType.LOADING_ERROR)));
    }
}