package com.jmucientes.udacity.bakingapp.home.mobius;

import android.support.annotation.NonNull;

import com.jmucientes.udacity.bakingapp.data.RecipeRepository;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEffect;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeLogic;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeModel;
import com.jmucientes.udacity.bakingapp.home.mobius.effecthandlers.HomeEffectHandlers;
import com.spotify.mobius.Mobius;
import com.spotify.mobius.MobiusLoop;
import com.spotify.mobius.android.AndroidLogger;
import com.spotify.mobius.android.runners.MainThreadWorkRunner;
import com.spotify.mobius.rx2.RxMobius;

import javax.inject.Inject;

public class HomeInjector {

    @Inject
    RecipeRepository mRecipeRepository;

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
        return RxMobius.loop(HomeLogic::update, HomeEffectHandlers.provideEffectHandler(mRecipeRepository))
                        .init(HomeLogic::init)
                        .logger(AndroidLogger.tag("BakingApp"));
    }
}
