package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import android.support.annotation.NonNull;

import com.spotify.mobius.First;
import com.spotify.mobius.Next;

import static com.spotify.mobius.First.first;

public final class HomeLogic {
    private HomeLogic() {}

    @NonNull
    public static First<HomeModel, HomeEffect> init(HomeModel model) {
        return first(model);
    }

    public static Next<HomeModel, HomeEffect> update(HomeModel model, HomeEvent event) {

        return event.map(
                recipeCardClicked -> onRecipeCardClicked(model, recipeCardClicked),
                recipesLoaded -> onRecipesLoaded(model, recipesLoaded)

        );
    }

    private static Next<HomeModel, HomeEffect> onRecipeCardClicked(HomeModel model, HomeEvent event) {
        return null;
    }

    private static Next<HomeModel,HomeEffect> onRecipesLoaded(HomeModel model, HomeEvent event) {
        return null;
    }
}