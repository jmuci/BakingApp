package com.jmucientes.udacity.bakingapp.di;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jmucientes.udacity.bakingapp.BakingApp;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoPlayerModule {

    private static SimpleExoPlayer mExoPlayer;

    @ApplicationScope
    @Provides
    static SimpleExoPlayer simpleExoPlayerProvider(BakingApp bakingApp, TrackSelector trackSelector, LoadControl loadControl) {
        if (mExoPlayer == null) {
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(bakingApp, trackSelector, loadControl);
        }
        return mExoPlayer;
    }

    @ApplicationScope
    @Provides
    static DataSource.Factory dataSourceFactoryProvider(BakingApp bakingApp) {
        return new DefaultDataSourceFactory(bakingApp,
                Util.getUserAgent(bakingApp, "BakingApp"));
    }

    @ApplicationScope
    @Provides
    static TrackSelector trackSelectorProvider() {
        return new DefaultTrackSelector();
    }

    @ApplicationScope
    @Provides
    static LoadControl loadControlProvider() {
        return new DefaultLoadControl();
    }
}
