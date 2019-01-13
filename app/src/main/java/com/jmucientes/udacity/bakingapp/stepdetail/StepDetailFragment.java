package com.jmucientes.udacity.bakingapp.stepdetail;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.jmucientes.udacity.bakingapp.model.Step;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class StepDetailFragment extends DaggerFragment {

    public static final String ARG_RECIPE = "recipe_parcelable";
    public static final String ARG_STEP_INDEX = "step_index";
    private final static String TAG = StepDetailFragment.class.getName();

    private TextView mStepDescription;
    private SimpleExoPlayerView mPlayerView;

    @Inject
    VideoPlayerHelper mVideoPlayerHelper;

    private Recipe mRecipe;
    private int mIndex;
    private Step mStep;
    private ImageView mThumbnailImage;

    @Inject
    public StepDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_detail_fragment, container, false);
        mStepDescription = view.findViewById(R.id.step_description);
        mPlayerView = view.findViewById(R.id.player_view_surface);
        final ImageButton previousStepButton = view.findViewById(R.id.previousStepButton);
        final ImageButton nextStepButton = view.findViewById(R.id.nextStepButton);
        mThumbnailImage = view.findViewById(R.id.step_thumbnail_image);

        bindStepDetailsFieldsAndViewsFromArgs(getArguments());

        if (savedInstanceState != null) {
            long position = savedInstanceState.getLong(VideoPlayerHelper.PLAYER_CURRENT_POS_KEY);
            boolean isPlayerReady = savedInstanceState.getBoolean(VideoPlayerHelper.PLAYER_IS_READY_KEY);
            int window = savedInstanceState.getInt(VideoPlayerHelper.PLAYER_WINDOW_KEY);

            mVideoPlayerHelper.restorePlaybackState(position, isPlayerReady, window);
        }

        previousStepButton.setOnClickListener(v -> previousStepClicked());
        nextStepButton.setOnClickListener(v -> nextStepClicked());
        return view;
    }

    private void bindStepDetailsFieldsAndViewsFromArgs(@Nullable Bundle extras) {
        if (extras != null) {
            mRecipe = extras.getParcelable(ARG_RECIPE);
            mIndex = extras.getInt(ARG_STEP_INDEX, -1);
            if (mRecipe != null && mIndex != -1) {
                mStep = mRecipe.getSteps().get(mIndex);
                mStepDescription.setText(mStep.getDescription());
                //TODO Add thumbnail
                final String thumbnailUrl = mStep.getThumbnailURL();
                if (!TextUtils.isEmpty(thumbnailUrl)) {
                    Picasso.get().load(thumbnailUrl).into(mThumbnailImage);
                } else {
                    mThumbnailImage.setVisibility(View.GONE);
                }
            } else {
                Log.e(TAG, "Got invalid recipe - index combo.");
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mVideoPlayerHelper.savePlayerStateIntoBundle(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!TextUtils.isEmpty(mStep.getVideoURL())) {
            mVideoPlayerHelper.initializePlayer(mPlayerView, Uri.parse(mStep.getVideoURL()));
        } else {
            mPlayerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mVideoPlayerHelper.releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showStep(int stepNumber) {
        Configuration config = this.getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            // use a grid layout manager
            ((MainActivity) Objects.requireNonNull(getActivity())).loadSecondFragmentOnScreen(mRecipe, stepNumber);
        } else {
            navigateToStep(stepNumber);
        }
    }

    private void previousStepClicked() {
        if (mIndex > 0) {
            showStep(mIndex - 1);
        } else {
            Toast.makeText(getActivity(), "There are no previoys steps", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextStepClicked() {
        if (mIndex < mRecipe.getSteps().size() - 1) {
            showStep(mIndex + 1);
        } else {
            Toast.makeText(getActivity(), "There are no next steps", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToStep(int stepNumber) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(StepDetailFragment.ARG_RECIPE, mRecipe);
        args.putInt(StepDetailFragment.ARG_STEP_INDEX, stepNumber);
        stepDetailFragment.setArguments(args);

        ((MainActivity) Objects.requireNonNull(getActivity())).navigateToFragment(stepDetailFragment);
    }
}
