package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.model.Recipe;

@AutoValue
public abstract class HomeModel {

    public static final HomeModel DEFAULT = builder()
            .recipes(ImmutableList.of())
            .build();

    @Nullable
    public abstract ImmutableList<Recipe> recipes();

    public HomeModel withRecipes(ImmutableList<Recipe> recipes) {
        return toBuilder().recipes(recipes).build();
    }

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_HomeModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder recipes(ImmutableList<Recipe> recipes);
        public abstract HomeModel build();
    }
}
