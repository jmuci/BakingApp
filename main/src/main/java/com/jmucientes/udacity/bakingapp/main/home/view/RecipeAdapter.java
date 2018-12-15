package com.jmucientes.udacity.bakingapp.main.home.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.main.R;
import com.jmucientes.udacity.bakingapp.main.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> mRecipeList;

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;
        CardView mCardView;

        public RecipeViewHolder(@NonNull CardView itemView) {
            super(itemView);
            mCardView = itemView;
            mTextView = mCardView.findViewById(R.id.card_counter);
        }
    }

    public RecipeAdapter(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        CardView recipeCardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_view, parent, false);
        return new RecipeViewHolder(recipeCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        //TODO Remove sample code
        recipeViewHolder.mTextView.setText("Card number: " + i );
    }

    @Override
    public int getItemCount() {
        return mRecipeList != null? mRecipeList.size() : 0;
    }
}
