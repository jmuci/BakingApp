package com.jmucientes.udacity.bakingapp.stepdetail;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.jmucientes.udacity.bakingapp.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class StepDetailActivity extends DaggerAppCompatActivity {

    @Inject
    StepDetailFragment mStepDetailFragment;
    private String mToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get recipe steps
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(StepDetailFragment.ARG_BUNDLE_KEY)) {
            mStepDetailFragment.setArguments(intent.getBundleExtra(StepDetailFragment.ARG_BUNDLE_KEY));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.step_detail_container, mStepDetailFragment)
            .commitNow();

        setContentView(R.layout.step_detail_activity);
        // If the Video Fragment is Active and we are in landscape, make fullscreen.
        setFullScreenModeIfLandscapeOrientation();

    }

    private void setFullScreenModeIfLandscapeOrientation() {
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // code for portrait mode
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    // Hide the nav bar and status bar
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
    }

}
