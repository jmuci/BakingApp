package com.jmucientes.udacity.bakingapp.recipedetailslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.recipedetailslist.view.RecipeDetailsAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RecipeDetailListFragment extends DaggerFragment {

    @Inject
    RecipeDetailsAdapter mRecipeDetailsAdapter;

    private RecyclerView mDetailsRecyclerView;

    @Inject
    public RecipeDetailListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_details_list_fragment, container);
        mDetailsRecyclerView = view.findViewById(R.id.recipe_steps_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mDetailsRecyclerView.setLayoutManager(layoutManager);
        mDetailsRecyclerView.setAdapter(mRecipeDetailsAdapter);
        return view;
    }
}
