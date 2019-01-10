package com.jmucientes.udacity.bakingapp.recipedetailslist.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>  {


    private List<Ingredient> mIngredientsList;

    @Inject
    public IngredientsAdapter() {
        mIngredientsList = new ArrayList<>();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        private final TextView mIngredientName;
        private final TextView mQuantiy;
        private final TextView mMeasure;

        public IngredientsViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            mQuantiy = itemView.findViewById(R.id.quantity);
            mMeasure = itemView.findViewById(R.id.measure);
            mIngredientName = itemView.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ConstraintLayout ingredientDetails = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_list_item, parent, false);
        return new IngredientsAdapter.IngredientsViewHolder(ingredientDetails);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientsViewHolder ingredientsViewHolder, int position) {
        ingredientsViewHolder.mQuantiy.setText(String.format(Locale.US, "%.1f", mIngredientsList.get(position).getQuantity()));
        ingredientsViewHolder.mMeasure.setText(mIngredientsList.get(position).getMeasure().toLowerCase());
        ingredientsViewHolder.mIngredientName.setText(mIngredientsList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        return mIngredientsList.size();
    }

    public void updateDataSet(List<Ingredient> ingredients) {
        if (ingredients != null)
            mIngredientsList = ingredients;
    }
}
