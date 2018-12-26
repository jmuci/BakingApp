package com.jmucientes.udacity.bakingapp;

import android.os.Bundle;

import com.jmucientes.udacity.bakingapp.home.HomeFragment;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Lazy<HomeFragment> mHomeFragmentProvider;

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
    }
}
