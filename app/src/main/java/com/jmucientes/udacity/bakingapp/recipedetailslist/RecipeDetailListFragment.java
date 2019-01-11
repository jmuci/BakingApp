package com.jmucientes.udacity.bakingapp.recipedetailslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmucientes.udacity.bakingapp.MainActivity;
import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.jmucientes.udacity.bakingapp.recipedetailslist.view.RecipeDetailsAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class RecipeDetailListFragment extends DaggerFragment {

    public static final String ARG_RECIPE = "recipe_parcelable";
    @Inject
    RecipeDetailsAdapter mRecipeDetailsAdapter;

    private RecyclerView mDetailsRecyclerView;
    private CardView mIngredientsCard;
    private Recipe mCurrentRecipe;

    @Inject
    public RecipeDetailListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_details_list_fragment, container, false);
        mIngredientsCard = view.findViewById(R.id.ingredients_cardView);
        mDetailsRecyclerView = view.findViewById(R.id.recipe_steps_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mDetailsRecyclerView.setLayoutManager(layoutManager);
        mDetailsRecyclerView.setAdapter(mRecipeDetailsAdapter);

        Bundle extras = getArguments();
        if (extras != null) {
            mCurrentRecipe = extras.getParcelable(ARG_RECIPE);
            if (mCurrentRecipe != null) {
                mRecipeDetailsAdapter.updateDataSet(mCurrentRecipe);
            }
        }

        mIngredientsCard.setOnClickListener(v -> navigateToIngredientsFragment());
        return view;
    }

    private void navigateToIngredientsFragment() {
        IngredientsListFragment ingredientsListFragment = new IngredientsListFragment();
        Bundle args = new Bundle();
        args.putParcelable(RecipeDetailListFragment.ARG_RECIPE, mCurrentRecipe);
        ingredientsListFragment.setArguments(args);

        ((MainActivity) getActivity()).navigateToFragment(ingredientsListFragment);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
