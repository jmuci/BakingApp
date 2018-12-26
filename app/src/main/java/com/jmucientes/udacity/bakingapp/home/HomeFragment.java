package com.jmucientes.udacity.bakingapp.home;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.home.view.RecipeAdapter;
import com.jmucientes.udacity.bakingapp.home.view.RecipeViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    private RecipeViewModel mViewModel;
    private RecyclerView mRecipeCardsRV;
    private SwipeRefreshLayout mSwipeRefresh;


    @Inject
    RecipeAdapter mRecipeAdapter;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ProgressBar mProgressBar;

    @Inject
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        mRecipeCardsRV = view.findViewById(R.id.recipes_recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mSwipeRefresh = view.findViewById(R.id.main_swipe_container);

        mSwipeRefresh.setOnRefreshListener(() -> {
            refreshDataSetFromNetwork();
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecipeCardsRV.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecipeCardsRV.setLayoutManager(layoutManager);

        mRecipeCardsRV.setAdapter(mRecipeAdapter);
        return view;
    }

    private void refreshDataSetFromNetwork() {
        mViewModel.refreshData().observe(this, recipeList -> {
             if (recipeList != null && recipeList.size() > 0) {
                 mRecipeAdapter.updateDataSet(recipeList);
             } else {
                 Toast.makeText(this.getContext(), "Failed to contact server.", Toast.LENGTH_SHORT).show();
             }
             mSwipeRefresh.setRefreshing(false);
         });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel.class);
        requestDataSetLoadAndObserveChanges();
    }

    private void requestDataSetLoadAndObserveChanges() {
        mViewModel.getRecipeList().observe(this, recipeList -> {
            mProgressBar.setVisibility(View.GONE);
            if (recipeList != null && recipeList.size() > 0) {
                mRecipeAdapter.updateDataSet(recipeList);
            } else if (recipeList == null) {
                Toast.makeText(this.getContext(), "Failed to reach server. Try again later", Toast.LENGTH_LONG).show();
            }
        });
    }

}
