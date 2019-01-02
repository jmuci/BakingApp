package com.jmucientes.udacity.bakingapp.stepdetail.di;

import android.content.Context;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.di.scopes.ActivityScope;
import com.jmucientes.udacity.bakingapp.di.scopes.FragmentScoped;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class StepDetailModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract StepDetailFragment stepDetailFragment();


    @ActivityScope
    @Provides
    static SimpleExoPlayer simpleExoPlayerProvider(MainActivity mainActivity, TrackSelector trackSelector, LoadControl loadControl) {
        return ExoPlayerFactory.newSimpleInstance(mainActivity, trackSelector, loadControl);
    }

    @ActivityScope
    @Provides
    static TrackSelector trackSelectorProvider() {
        return new DefaultTrackSelector();
    }

    @ActivityScope
    @Provides
    static LoadControl loadControlProvider() {
        return new DefaultLoadControl();
    }
}
