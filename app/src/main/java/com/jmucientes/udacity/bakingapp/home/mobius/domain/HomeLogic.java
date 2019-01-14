package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import android.support.annotation.NonNull;

import com.spotify.mobius.First;

import static com.spotify.mobius.First.first;

public final class HomeLogic {
    private HomeLogic() {}

    @NonNull
    public static First<HomeModel, HomeEvent> init(HomeModel model) {
        return first(model);
    }


}