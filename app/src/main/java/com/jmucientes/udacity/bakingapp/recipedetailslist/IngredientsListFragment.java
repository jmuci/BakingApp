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
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.jmucientes.udacity.bakingapp.recipedetailslist.view.IngredientsAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class IngredientsListFragment extends DaggerFragment {

    public static final String ARG_RECIPE = "recipe_parcelable";
    @Inject
    IngredientsAdapter mIngredientsAdapter;

    private RecyclerView mIngredientsRecyclerView;

    @Inject
    public IngredientsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredients_list_fragment, container, false);
        mIngredientsRecyclerView = view.findViewById(R.id.ingredients_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mIngredientsRecyclerView.setLayoutManager(layoutManager);
        mIngredientsRecyclerView.setAdapter(mIngredientsAdapter);

        Bundle extras = getArguments();
        if (extras != null) {
            Recipe recipe = extras.getParcelable(ARG_RECIPE);
            if (recipe != null && recipe.getIngredients() != null) {
                mIngredientsAdapter.updateDataSet(recipe.getIngredients());
            }
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
