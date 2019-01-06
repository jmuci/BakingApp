package com.jmucientes.udacity.bakingapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.jmucientes.udacity.bakingapp.home.HomeFragment;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private static final String TAG = MainActivity.class.getName();

    @Inject
    Lazy<HomeFragment> mHomeFragmentProvider;
    private String mToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (homeFragment == null) {
                homeFragment = mHomeFragmentProvider.get();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                        .commitNow();
            }
        }

        // If the Video Fragment is Active and we are in landscape, make fullscreen.
        if (isVideoFragmentActive()) {
            setFullScreenModeIfLandscapeOrientation();
        }

        //Listen for changes in the back stack
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        //Handle when activity is recreated like on orientation Change
        shouldDisplayHomeUp();
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

    private boolean isVideoFragmentActive() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        return currentFragment instanceof StepDetailFragment;
    }

    @Override
    public void onBackStackChanged() {
        shouldDisplayHomeUp();
    }

    public void shouldDisplayHomeUp() {
        //Enable Up button only  if there are entries in the back stack
        boolean canGoBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(canGoBack);
            if (canGoBack) {
                getSupportActionBar().setTitle(mToolbarTitle);
            } else {
                getSupportActionBar().setTitle(R.string.app_name);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    public void navigateToFragmentAndSetToolbarTitle(Fragment fragment, String toolbarTitle) {
        mToolbarTitle = toolbarTitle;
        navigateToFragment(fragment);
    }

    public void navigateToFragment(Fragment fragment) {
        Log.d(TAG, "Navigating to fragment: " + fragment.toString());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
    }
}
