package com.jmucientes.udacity.bakingapp.recipedetailslist.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public DetailViewHolder(@NonNull TextView itemView) {
            super(itemView);
            mDetailsShortDescriptionTV = itemView;

        }
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        TextView stepTV = (TextView) LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new DetailViewHolder(stepTV);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int position) {
        detailViewHolder.mDetailsShortDescriptionTV.setText(mStepList.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepList.size();
    }
}
