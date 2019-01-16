package com.jmucientes.udacity.bakingapp.home.mobius.domain;

import com.spotify.mobius.test.InitSpec;

import org.junit.Before;
import org.junit.Test;

public class HomeLogicTest {

    private InitSpec<HomeModel, HomeEffect> initSpec;

    @Before
    public void setUp() throws Exception {
        initSpec = new InitSpec<>(HomeLogic::init);
    }

    @Test
    public void init() {
        initSpec.when(HomeModel.DEFAULT);
    }

    @Test
    public void update() {
    }
}