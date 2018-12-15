package com.jmucientes.udacity.bakingapp.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jmucientes.udacity.bakingapp.main.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow();
        }
    }
}
