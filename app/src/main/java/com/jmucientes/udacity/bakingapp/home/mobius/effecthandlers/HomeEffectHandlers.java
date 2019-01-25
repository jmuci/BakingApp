package com.jmucientes.udacity.bakingapp.home.mobius.effecthandlers;

import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.spotify.mobius.rx.RxMobius;

import rx.Observable;

public class HomeEffectHandlers {

    private HomeEffectHandlers() {
    }

    public Observable.Transformer<HomeEffect, HomeEvent> provideEffectHandler() {
        return RxMobius.<HomeEffect, HomeEvent>subtypeEffectHandler()
                .addAction(HomeEffect.RequestRecipes.class, this::handleRequestRecipes)
                .addConsumer(HomeEffect.NavigateToRecipeDetailsList.class, recipe -> handleNavigateToRecipe(recipe))
                .build();
    }

    private void handleNavigateToRecipe(HomeEffect.NavigateToRecipeDetailsList recipe) {

    }

    private void handleRequestRecipes() {
    }
}
