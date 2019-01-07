package com.jmucientes.udacity.bakingapp.recipedetailslist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Step;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailActivity;
import com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.jmucientes.udacity.bakingapp.stepdetail.StepDetailFragment.ARG_BUNDLE_KEY;

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.DetailViewHolder> {

    private List<Step> mStepList;
    private WeakReference<Context> mContextWeakReference;

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
        mContextWeakReference = new WeakReference<>(parent.getContext());
        ConstraintLayout stepTV = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.step_list_item, parent, false);
        return new DetailViewHolder(stepTV);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder detailViewHolder, int position) {
        detailViewHolder.mStepNumber.setText(String.valueOf(position + 1));
        detailViewHolder.mDetailsShortDescriptionTV.setText(mStepList.get(position).getShortDescription());

        detailViewHolder.mDetailsShortDescriptionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToStepDetails(mStepList.get(position));
            }
        });
    }

    private void navigateToStepDetails(Step step) {
        Intent seeDetailsIntent = new Intent(mContextWeakReference.get(), StepDetailActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(StepDetailFragment.ARG_STEP_KEY, step);
        //args.putParcelable(StepDetailFragment.STEPS_LIST_KEY, mStepList);
        seeDetailsIntent.putExtra(ARG_BUNDLE_KEY, args);
        mContextWeakReference.get().startActivity(seeDetailsIntent);
/*
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BUNDLE_KEY, step);
        stepDetailFragment.setArguments(args);

        ((MainActivity) mContextWeakReference.get()).navigateToFragment(stepDetailFragment);*/
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
