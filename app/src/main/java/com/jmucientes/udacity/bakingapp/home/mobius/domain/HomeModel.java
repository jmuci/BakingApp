package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;
import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.io.Serializable;

@AutoValue
public abstract class HomeModel implements Serializable {

    public static final HomeModel DEFAULT = builder()
            .recipes(ImmutableList.of())
            .loading(true)
            .refreshing(false)
            .build();

    @Nullable
    public abstract ImmutableList<Recipe> recipes();

    public abstract Boolean loading();

    public abstract Boolean refreshing();

    public HomeModel withRecipes(ImmutableList<Recipe> recipes) {
        return toBuilder().recipes(recipes).build();
    }

    public HomeModel withLoading(Boolean loading) {
        return toBuilder().loading(loading).build();
    }

    public HomeModel withRefreshing(Boolean refreshing) {
        return toBuilder().refreshing(refreshing).build();
    }

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_HomeModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder recipes(ImmutableList<Recipe> recipes);
        public abstract Builder loading(Boolean loading);
        public abstract Builder refreshing(Boolean refreshing);

        public abstract HomeModel build();
    }
}
