package com.jmucientes.udacity.bakingapp.home.mobius;

import android.support.annotation.NonNull;

import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeModel;
import com.spotify.mobius.Mobius;
import com.spotify.mobius.MobiusLoop;
import com.spotify.mobius.android.runners.MainThreadWorkRunner;

import javax.inject.Inject;

public class HomeInjector {

    @Inject
    public HomeInjector() {
    }

    public MobiusLoop.Controller<HomeModel, HomeEvent> createController(
            @NonNull HomeModel defaultModel) {
        return Mobius.controller(
                createLoopFactory(),
                defaultModel,
                MainThreadWorkRunner.create());

    }

    private MobiusLoop.Factory<HomeModel, HomeEvent, HomeEffect>  createLoopFactory() {
        return null;
    }
}
