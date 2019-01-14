package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.google.auto.value.AutoValue;
import com.jmucientes.udacity.bakingapp.data.network.ConnectionState;
import com.jmucientes.udacity.bakingapp.model.RecipesModel;

@AutoValue
public abstract class HomeModel {

    public static final HomeModel DEFAULT = builder()
            .connectionState(null)
            .recipesModel(null)
            .build();

    public abstract ConnectionState connectionState();
    public abstract RecipesModel recipesModel();

    public HomeModel withConnectionState(ConnectionState state) {
        return toBuilder().connectionState(state).build();
    }

    public HomeModel withRecipesModel(RecipesModel recipesModel) {
        return toBuilder().recipesModel(recipesModel).build();
    }

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_HomeModel.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder connectionState(ConnectionState state);
        public abstract Builder recipesModel(RecipesModel recipesModel);
        public abstract HomeModel build();
    }
}
