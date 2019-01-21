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

import static com.google.common.collect.ImmutableList.*;
import static com.spotify.mobius.test.InitSpec.assertThatFirst;
import static com.spotify.mobius.test.NextMatchers.hasEffects;
import static com.spotify.mobius.test.NextMatchers.hasModel;
import static com.spotify.mobius.test.NextMatchers.hasNoEffects;
import static com.spotify.mobius.test.NextMatchers.hasNoModel;
import static com.spotify.mobius.test.NextMatchers.hasNothing;
import static com.spotify.mobius.test.UpdateSpec.assertThatNext;

/**
 * Test suite to test the update() function in the {@HomeLogic}
 */
@RunWith(JUnit4.class)
public class HomeLogicTest {

    private InitSpec<HomeModel, HomeEffect> initSpec;
    private UpdateSpec<HomeModel, HomeEvent, HomeEffect> updateSpec;

    @Before
    public void setUp() {
        initSpec = new InitSpec<>(HomeLogic::init);
        updateSpec = new UpdateSpec<>(HomeLogic::update);
    }

    @Test
    public void testInitState() {
        initSpec.when(HomeModel.DEFAULT)
            .then(assertThatFirst(FirstMatchers.hasModel(HomeModel.DEFAULT.withRecipes(of()))));
    }

    @Test
    public void loadingRecipesWhenInitState_shouldUpdatemodel() {
        ImmutableList<Recipe> receivedRecipes = of(createRecipe("LemonCake"), createRecipe("Brownie"));
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(receivedRecipes).build();
        updateSpec.given(HomeModel.DEFAULT)
                .when(HomeEvent.recipesLoaded(receivedRecipes))
                .then(assertThatNext(hasModel(stateWithSomeRecipes.withRecipes(receivedRecipes)), hasNoEffects()));
    }

    @Test
    public void loadingSameRecipeListThatIsAlreadyInModel_shouldNotUpdateModel() {
        ImmutableList<Recipe> receivedRecipes = of(createRecipe("LemonCake"), createRecipe("Brownie"));
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(receivedRecipes).build();
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipesLoaded(receivedRecipes))
                .then(assertThatNext(hasNothing()));
    }

    @Test
    public void loadingNewRecipes_shouldUpdateModelReplacingOldRecipes() {
        ImmutableList<Recipe> receivedRecipes = of(createRecipe("LemonCake"), createRecipe("Brownie"));
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(receivedRecipes).build();
        HomeModel initialState = HomeModel.builder().recipes(of(createRecipe("Cheesecake"))).build();
        updateSpec.given(initialState)
                .when(HomeEvent.recipesLoaded(receivedRecipes))
                .then(assertThatNext(hasModel(stateWithSomeRecipes.withRecipes(receivedRecipes)), hasNoEffects()));
    }

    @Test
    public void loadingEmptyListOfRecipes_shouldNotUpdateModel() {
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(of(createRecipe("LemonCake"), createRecipe("Brownie"))).build();
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipesLoaded(of()))
                .then(assertThatNext(hasNothing()));
    }

    @Test
    public void clickingOnACardWithPopulatedModel_shouldNavigateToCardRecipeDetails() {
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(of(createRecipe("LemonCake"), createRecipe("Brownie"))).build();
        Recipe clickedRecipe = createRecipe("LemonCake");
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipeCardClicked(clickedRecipe))
                .then(assertThatNext(hasNoModel(), hasEffects(HomeEffect.navigateToRecipeDetailsList(clickedRecipe))));
    }

    @Test
    public void clickingOnACardWithEmptyModel_shouldDoNothing() {
        // It should be impossible from a user perspective.
        HomeModel stateWithSomeRecipes = HomeModel.builder().recipes(of()).build();
        Recipe clickedRecipe = createRecipe("LemonCake");
        updateSpec.given(stateWithSomeRecipes)
                .when(HomeEvent.recipeCardClicked(clickedRecipe))
                .then(assertThatNext(hasNothing()));
    }

    private Recipe createRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        return recipe;
    }
}