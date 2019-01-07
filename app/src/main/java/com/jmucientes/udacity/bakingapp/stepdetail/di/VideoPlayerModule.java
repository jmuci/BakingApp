package com.jmucientes.udacity.bakingapp.stepdetail.di;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.di.scopes.FragmentScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoPlayerModule {

    @FragmentScoped
    @Provides
    static SimpleExoPlayer simpleExoPlayerProvider(MainActivity mainActivity, TrackSelector trackSelector, LoadControl loadControl) {
        return  ExoPlayerFactory.newSimpleInstance(mainActivity, trackSelector, loadControl);
    }

    @FragmentScoped
    @Provides
    static DataSource.Factory dataSourceFactoryProvider(MainActivity mainActivity) {
        return new DefaultDataSourceFactory(mainActivity,
                Util.getUserAgent(mainActivity, "BakingApp"));
    }

    @FragmentScoped
    @Provides
    static TrackSelector trackSelectorProvider() {
        return new DefaultTrackSelector();
    }

    @FragmentScoped
    @Provides
    static LoadControl loadControlProvider() {
        return new DefaultLoadControl();
    }
}
