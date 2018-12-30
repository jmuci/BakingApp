package com.jmucientes.udacity.bakingapp.recipedetailslist.view;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Step;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.DetailViewHolder> {

    private List<Step> mStepList;

    @Inject
    public RecipeDetailsAdapter() {
        mStepList = new ArrayList<>();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        private final TextView mDetailsShortDescriptionTV;
        private final TextView mStepNumber;

        public DetailViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            mStepNumber = itemView.findViewById(R.id.step_number);
            mDetailsShortDescriptionTV = itemView.findViewById(R.id.short_desc);

        }
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        ConstraintLayout stepTV = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new DetailViewHolder(stepTV);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int position) {
        detailViewHolder.mStepNumber.setText(String.valueOf(position + 1));
        detailViewHolder.mDetailsShortDescriptionTV.setText(mStepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }

    public void updateDataSet(List<Step> steps) {
        if (steps != null)
            mStepList = steps;
    }
}
