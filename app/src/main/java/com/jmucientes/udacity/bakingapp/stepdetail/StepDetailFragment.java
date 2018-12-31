package com.jmucientes.udacity.bakingapp.stepdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Step;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class StepDetailFragment extends DaggerFragment {

    public static final String ARG_STEP = "step_parcelable";;

    private TextView mStepDescription;

    @Inject
    public StepDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_detail_fragment, container, false);
        mStepDescription = view.findViewById(R.id.step_description);

        Bundle extras = getArguments();
        if (extras != null) {
            Step step = extras.getParcelable(ARG_STEP);
            if (step !=null )
                mStepDescription.setText(step.getDescription());
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
