package com.jmucientes.udacity.bakingapp.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Step;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class StepDetailFragment extends DaggerFragment {

    public static final String ARG_STEP = "step_parcelable";
    private final static String TAG = StepDetailFragment.class.getName();
    public static final String PLAYER_CURRENT_POS_KEY = "player_current_pos";
    public static final String PLAYER_IS_READY_KEY = "player_is_ready";

    private TextView mStepDescription;
    private SimpleExoPlayerView mPlayerView;

    @Inject
    SimpleExoPlayer mPlayer;
    @Inject
    DataSource.Factory mDataSourceFactory;

    @Inject
    public StepDetailFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_detail_fragment, container, false);
        mStepDescription = view.findViewById(R.id.step_description);
        mPlayerView = view.findViewById(R.id.player_view);

        Bundle extras = getArguments();
        if (extras != null) {
            Step step = extras.getParcelable(ARG_STEP);
            if (step != null) {
                mStepDescription.setText(step.getDescription());
                if (!TextUtils.isEmpty(step.getVideoURL())) {
                    initializePlayer(Uri.parse(step.getVideoURL()), savedInstanceState);
                } else {
                    mPlayerView.setVisibility(View.GONE);
                }
            }
        }
        return view;
    }

    private void initializePlayer(Uri mp4VideoUri, Bundle savedInstanceState) {
        if (mPlayer != null && mp4VideoUri != null) {
            mPlayerView.setPlayer(mPlayer);
            // Produces DataSource instances through which media data is loaded.
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri, mDataSourceFactory, new DefaultExtractorsFactory(), null, null);
            // Prepare the player with the source.
            final boolean restoreStateFromBundle = resumePlaybackFromStateBundle(savedInstanceState) ;
            mPlayer.prepare(videoSource, restoreStateFromBundle, false);
            if (!restoreStateFromBundle)
                mPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(PLAYER_CURRENT_POS_KEY, Math.max(0, mPlayer.getCurrentPosition()));
        outState.putBoolean(PLAYER_IS_READY_KEY, mPlayer.getPlayWhenReady());
    }

    private boolean resumePlaybackFromStateBundle(@Nullable Bundle inState) {
        if (inState != null) {
            mPlayer.setPlayWhenReady(inState.getBoolean(PLAYER_IS_READY_KEY));
            mPlayer.seekTo(inState.getLong(PLAYER_CURRENT_POS_KEY));
            return true;
        }
        return false;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}
