package com.jmucientes.udacity.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.jmucientes.udacity.bakingapp.home.HomeFragment;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private static final String TAG = MainActivity.class.getName();

    //TODO Remove Lazy Injection
    @Inject
    HomeFragment mHomeFragment;
    private String mToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        // Happens only the first time
        if (savedInstanceState == null) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container);
            if (homeFragment == null) {
                homeFragment = mHomeFragment;
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                        .commitNow();
            }
        }

        //Listen for changes in the back stack
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        //Enable up navigation
        displayHomeUpIfEntriesInBackStack();
    }

    @Override
    public void onBackStackChanged() {
        displayHomeUpIfEntriesInBackStack();
    }

    private void displayHomeUpIfEntriesInBackStack() {
        //Enable Up button only if there are entries in the back stack
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
