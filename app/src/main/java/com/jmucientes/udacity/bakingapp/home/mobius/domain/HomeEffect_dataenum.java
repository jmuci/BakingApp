package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.spotify.dataenum.DataEnum;
import com.spotify.dataenum.dataenum_case;

@DataEnum
interface HomeEffect_dataenum {

    dataenum_case RequestRecipes();
    dataenum_case NavigateToRecipeDetailsList(Recipe recipe);
}
