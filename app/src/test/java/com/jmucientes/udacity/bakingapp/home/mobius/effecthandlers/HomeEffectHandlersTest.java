package com.jmucientes.udacity.bakingapp.home.mobius.effecthandlers;

import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.FeedbackType;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.RequestRecipes;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.subjects.PublishSubject;

import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.requestRecipes;
import static com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect.showFeedback;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeEffectHandlersTest {

    @Mock
    Action action;

    @Mock
    RecipeRepository mockRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testHandleRequestRecipes() {

        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(createRecipe("Lemon Cake"));
        recipeList.add(createRecipe("Brownie"));

        when(mockRepository.getRecipesMaybe()).thenReturn(Maybe.just(recipeList));

        ObservableTransformer<RequestRecipes, HomeEvent> underTest = HomeEffectHandlers.handleRequestRecipes(mockRepository);
        TestCase<RequestRecipes, HomeEvent> testCase = new TestCase<>(underTest);
        testCase.dispatchEffect(requestRecipes().asRequestRecipes());

        testCase.assertEvents(HomeEvent.recipesLoaded(ImmutableList.copyOf(recipeList)));
    }

    @Test
    public void testHandleShowLoadingErrorFeedback() throws Exception {
        Consumer<HomeEffect.ShowFeedback> underTest = HomeEffectHandlers.handleShowFeedback(action);
        underTest.accept(showFeedback(FeedbackType.LOADING_ERROR).asShowFeedback());
        verify(action).run();
    }



    static class TestCase<F, E> {
        final PublishSubject<F> upstream = PublishSubject.create();
        final TestObserver<E> observer = new TestObserver<>();

        TestCase(ObservableTransformer<F, E> underTest) {
            upstream.compose(underTest).subscribe(observer);
        }

        public void dispatchEffect(F effect) {
            upstream.onNext(effect);
        }

        @SafeVarargs
        public final void assertEvents(E... events) {
            observer.assertValues(events);
        }
    }

    private Recipe createRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        return recipe;
    }
}