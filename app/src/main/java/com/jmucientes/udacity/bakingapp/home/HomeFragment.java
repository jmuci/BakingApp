package com.jmucientes.udacity.bakingapp.home;

import android.arch.lifecycle.ViewModelProvider;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.home.mobius.HomeInjector;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeEvent;
import com.jmucientes.udacity.bakingapp.home.mobius.domain.HomeModel;
import com.jmucientes.udacity.bakingapp.home.view.RecipeAdapter;
import com.jmucientes.udacity.bakingapp.home.view.RecipeViewModel;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.jmucientes.udacity.bakingapp.recipedetailslist.RecipeDetailListFragment;
import com.spotify.mobius.Connection;
import com.spotify.mobius.MobiusLoop;
import com.spotify.mobius.functions.Consumer;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    private static final String TAG = HomeFragment.class.getName();
    private RecipeViewModel mViewModel;
    private RecyclerView mRecipeCardsRV;
    private SwipeRefreshLayout mSwipeRefresh;


    @Inject
    RecipeAdapter mRecipeAdapter;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    @Inject
    HomeInjector mHomeInjector;

    private ProgressBar mProgressBar;
    private MobiusLoop.Controller<HomeModel, HomeEvent> mController;
    private LinearLayout mErrorView;

    @Inject
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        mRecipeCardsRV = view.findViewById(R.id.recipes_recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mSwipeRefresh = view.findViewById(R.id.main_swipe_container);
        mErrorView = view.findViewById(R.id.loading_error_view);

        mController = mHomeInjector.createController(
                HomeModel.DEFAULT,
                this::navigateToStepDetailsViewFragment,
                this::showErrorView);

        mController.connect(this::connectViews);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecipeCardsRV.setHasFixedSize(true);

        LinearLayoutManager layoutManager;
        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600) {
            // use a grid layout manager
            layoutManager = new GridLayoutManager(this.getContext(), 3);
        } else {
            // use a linear layout manager
            layoutManager = new LinearLayoutManager(this.getContext());
        }

        mRecipeCardsRV.setLayoutManager(layoutManager);

        mRecipeCardsRV.setAdapter(mRecipeAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RecipeViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        mController.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mController.stop();
    }

    private Connection<HomeModel> connectViews(Consumer<HomeEvent> eventConsumer) {
        addUiListeners(eventConsumer);
        return new Connection<HomeModel>() {
            public void accept(@NonNull HomeModel model) {

                Log.d(TAG, "connectViews().accept() Model: " + model.recipes());
                // this will be called whenever there is a new model
                if (model.recipes().size() > 0) {
                    mRecipeAdapter.updateDataSet(model.recipes());
                    mErrorView.setVisibility(View.GONE);
                }

                if (model.loading()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                } else {
                    mProgressBar.setVisibility(View.GONE);
                }

                //Update Refreshing view for Pull to Refresh
                mSwipeRefresh.setRefreshing(model.refreshing());
            }

            public void dispose() {
                // don't forget to remove listeners when the UI is disconnected
                //button.setOnClickListener(null);
            }
        };
    }

    private void addUiListeners(Consumer<HomeEvent> eventConsumer) {
        mRecipeAdapter.setItemListener(recipe -> eventConsumer.accept(HomeEvent.recipeCardClicked(recipe)));
        mSwipeRefresh.setOnRefreshListener(() -> eventConsumer.accept(HomeEvent.refreshRecipes()));
    }

    public void navigateToStepDetailsViewFragment(Recipe recipe) {
        Log.d(TAG, "navigateToStepDetailsViewFragment(), Recipe: " + recipe.getName());
        RecipeDetailListFragment recipeDetailListFragment = new RecipeDetailListFragment();
        Bundle args = new Bundle();
        args.putParcelable(RecipeDetailListFragment.ARG_RECIPE, recipe);
        recipeDetailListFragment.setArguments(args);

        ((MainActivity) Objects.requireNonNull(getActivity())).navigateToFragmentAndSetToolbarTitle(recipeDetailListFragment, recipe.getName());
    }

    public void showErrorView() {
        mErrorView.setVisibility(View.VISIBLE);
    }

}
