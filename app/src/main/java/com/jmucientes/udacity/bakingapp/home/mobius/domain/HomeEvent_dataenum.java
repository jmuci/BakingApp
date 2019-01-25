package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.spotify.dataenum.DataEnum;
import com.spotify.dataenum.dataenum_case;

@DataEnum
interface HomeEvent_dataenum {

    dataenum_case RecipeCardClicked(Recipe recipe);
    dataenum_case RecipesLoaded(ImmutableList<Recipe> recipes);

    //Pull to Refresh event
}
