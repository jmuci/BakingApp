package com.jmucientes.udacity.bakingapp.stepdetail.di;

import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module(includes = VideoPlayerModule.class)
public abstract class StepDetailModule {

    @ContributesAndroidInjector()
    abstract StepDetailFragment stepDetailFragment();

}
