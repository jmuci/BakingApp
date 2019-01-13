package com.jmucientes.udacity.bakingapp.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;

import javax.inject.Provider;


public class VideoPlayerHelper {

    private final static String TAG = VideoPlayerHelper.class.getName();
    public static final String PLAYER_CURRENT_POS_KEY = "player_current_pos";
    public static final String PLAYER_IS_READY_KEY = "player_is_ready";
    public static final String PLAYER_WINDOW_KEY = "player_window";

    private  SimpleExoPlayer mPlayer;

    private Provider<SimpleExoPlayer> mSimpleExoPlayerProvider;
    private final DataSource.Factory mDataSourceFactory;
    //Player state
    private long mPlaybackPosition = 0;
    private boolean mPlayWhenReady = true;
    private int mCurrentWindow = 0;

    public VideoPlayerHelper(Provider<SimpleExoPlayer> simpleExoPlayerProvider, DataSource.Factory dataSourceFactory) {
        mSimpleExoPlayerProvider = simpleExoPlayerProvider;
        mPlayer = mSimpleExoPlayerProvider.get();
        mDataSourceFactory = dataSourceFactory;

    }

    public VideoPlayerHelper(Provider<SimpleExoPlayer> simpleExoPlayerProvider,
                             DataSource.Factory dataSourceFactory,
                             long playbackPosition,
                             boolean playWhenReady,
                             int currentWindow) {
        mSimpleExoPlayerProvider = simpleExoPlayerProvider;
        mDataSourceFactory = dataSourceFactory;
        mPlayer = mSimpleExoPlayerProvider.get();
        mPlaybackPosition = playbackPosition;
        mPlayWhenReady = playWhenReady;
        mCurrentWindow = currentWindow;

    }

    public void initializePlayer(@NonNull SimpleExoPlayerView playerView, @Nullable Uri mp4VideoUri) {
        if (mp4VideoUri != null) {
            if (mPlayer == null) {
                // Get new instance of player if mPlayer is null
                mPlayer = mSimpleExoPlayerProvider.get();
            }
            playerView.setPlayer(mPlayer);

            mPlayer.setPlayWhenReady(mPlayWhenReady);
            mPlayer.seekTo(mCurrentWindow, mPlaybackPosition);
            MediaSource mediaSource = new ExtractorMediaSource(mp4VideoUri, mDataSourceFactory, new DefaultExtractorsFactory(), null, null);
            mPlayer.prepare(mediaSource, true, false);
        }
    }


    public Bundle savePlayerStateIntoBundle(@NonNull Bundle outState) {
        if (mPlayer != null) {
            mPlaybackPosition = Math.max(0, mPlayer.getCurrentPosition());
            outState.putLong(PLAYER_CURRENT_POS_KEY, mPlaybackPosition);

            mPlayWhenReady = mPlayer.getPlayWhenReady();
            outState.putBoolean(PLAYER_IS_READY_KEY, mPlayWhenReady);

            mCurrentWindow = mPlayer.getCurrentWindowIndex();
            outState.putInt(PLAYER_WINDOW_KEY, mCurrentWindow);
        } else {
            Log.e(TAG, "Could not save state, mPlayer was null!!!");
        }
        return outState;
    }

    public void releasePlayer() {
        if (mPlayer != null) {
            mPlaybackPosition = mPlayer.getCurrentPosition();
            mCurrentWindow = mPlayer.getCurrentWindowIndex();
            mPlayWhenReady = mPlayer.getPlayWhenReady();
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }


}
