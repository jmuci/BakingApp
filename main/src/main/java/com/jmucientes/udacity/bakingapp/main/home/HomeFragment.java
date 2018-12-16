package com.jmucientes.udacity.bakingapp.main.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmucientes.udacity.bakingapp.main.R;
import com.jmucientes.udacity.bakingapp.main.home.view.HomeViewModel;
import com.jmucientes.udacity.bakingapp.main.home.view.RecipeAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    private HomeViewModel mViewModel;
    private RecyclerView mRecipeCardsRV;
    @Inject
    RecipeAdapter mRecipeAdapter;

    @Inject
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        mRecipeCardsRV = view.findViewById(R.id.recipes_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecipeCardsRV.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecipeCardsRV.setLayoutManager(layoutManager);

        //TODO Remove fake list
        //mRecipeAdapter = new RecipeAdapter(new ArrayList<>(Arrays.asList(new Recipe(), new Recipe(), new Recipe())));
        mRecipeCardsRV.setAdapter(mRecipeAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Inject with Dagger
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        mViewModel.getRecipeList().observe(this, recipeList -> mRecipeAdapter.updateDataSet(recipeList));
        //mViewModel.init(null);
    }

}
