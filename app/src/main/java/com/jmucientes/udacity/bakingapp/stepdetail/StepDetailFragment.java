package com.jmucientes.udacity.bakingapp.stepdetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Step;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class StepDetailFragment extends DaggerFragment {

    public static final String ARG_STEP = "step_parcelable";
    ;

    private TextView mStepDescription;
    private SimpleExoPlayerView mPlayerView;

    @Inject
    SimpleExoPlayer mPlayer;

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
                initializePlayer(Uri.parse(step.getVideoURL()));
            }
        }

        return view;
    }

    private void initializePlayer(Uri mp4VideoUri) {
        if (mPlayer != null && mp4VideoUri != null) {
            mPlayerView.setPlayer(mPlayer);
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                    Util.getUserAgent(getActivity(), "BakingApp"));
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource(mp4VideoUri, dataSourceFactory, new DefaultExtractorsFactory(), null, null);
            // Prepare the player with the source.
            mPlayer.prepare(videoSource);
            mPlayer.setPlayWhenReady(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }
}
