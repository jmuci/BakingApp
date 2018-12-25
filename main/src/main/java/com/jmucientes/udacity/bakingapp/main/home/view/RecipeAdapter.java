package com.jmucientes.udacity.bakingapp.main.home.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.main.R;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private static final String TAG = RecipeAdapter.class.getName();
    private List<Recipe> mRecipeList;

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mCounterTv;
        private final TextView mTitleTv;
        CardView mCardView;

        public RecipeViewHolder(@NonNull CardView itemView) {
            super(itemView);
            mCardView = itemView;
            mTitleTv = mCardView.findViewById(R.id.card_title);
            mCounterTv = mCardView.findViewById(R.id.card_counter);
        }
    }

    @Inject
    public RecipeAdapter() {
        mRecipeList = new ArrayList<>();
    }

    public void updateDataSet(List<Recipe> recipeList) {
        if (recipeList != null)
            Log.d(TAG, "Updating data set. List size : " + recipeList.size());
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        CardView recipeCardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false);
        return new RecipeViewHolder(recipeCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int pos) {
        //TODO Remove sample code
        recipeViewHolder.mTitleTv.setText(mRecipeList.get(pos).getName());
        recipeViewHolder.mCounterTv.setText("Card number: " + pos);
    }

    @Override
    public int getItemCount() {
        return mRecipeList != null? mRecipeList.size() : 0;
    }
}
