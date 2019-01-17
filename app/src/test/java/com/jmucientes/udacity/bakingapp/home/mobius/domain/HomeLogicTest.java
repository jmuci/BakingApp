package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.spotify.mobius.test.FirstMatchers;
import com.spotify.mobius.test.InitSpec;
import com.spotify.mobius.test.UpdateSpec;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.spotify.mobius.test.InitSpec.assertThatFirst;
import static com.spotify.mobius.test.NextMatchers.hasModel;
import static com.spotify.mobius.test.NextMatchers.hasNoEffects;
import static com.spotify.mobius.test.UpdateSpec.assertThatNext;

@RunWith(JUnit4.class)
public class HomeLogicTest {

    private InitSpec<HomeModel, HomeEffect> initSpec;
    private UpdateSpec<HomeModel, HomeEvent, HomeEffect> updateSpec;

    @Before
    public void setUp() throws Exception {
        initSpec = new InitSpec<>(HomeLogic::init);
        updateSpec = new UpdateSpec<>(HomeLogic::update);
    }

    @Test
    public void init() {
        initSpec.when(HomeModel.DEFAULT)
            .then(assertThatFirst(FirstMatchers.hasModel(HomeModel.DEFAULT.withRecipes(ImmutableList.of()))));
    }

    @Test
    public void testUpdateWhenEventRecipesLoadedWithInitState() {
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(ImmutableList.of(recipe("LemonCake"), recipe("Brownie"))).build();
        updateSpec.given(HomeModel.DEFAULT)
                .when(HomeEvent.recipesLoaded(ImmutableList.of(recipe("LemonCake"), recipe("Brownie"))))
                .then(assertThatNext(hasModel(stateWithSomeRecipes)));
    }

    @Test
    public void testUpdateWhenEmptyRecipesLoadedEventWithPopulatedModel() {
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(ImmutableList.of(recipe("LemonCake"), recipe("Brownie"))).build();
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipesLoaded(ImmutableList.of()))
                .then(assertThatNext(hasModel(), hasNoEffects()));
    }

    @Test
    public void testUpdateWhenRecipeCardClickedEventWithPopulatedModel() {
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(ImmutableList.of(recipe("LemonCake"), recipe("Brownie"))).build();
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipeCardClicked())
                .then(assertThatNext(hasModel(), hasNoEffects()));
    }

    private Recipe recipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        return recipe;
    }
}