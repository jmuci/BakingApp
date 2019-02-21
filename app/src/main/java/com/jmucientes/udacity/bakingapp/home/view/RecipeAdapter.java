package com.jmucientes.udacity.bakingapp.home.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmucientes.udacity.bakingapp.R;
import com.jmucientes.udacity.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private static final String TAG = RecipeAdapter.class.getName();
    private List<Recipe> mRecipeList;
    private WeakReference<Context> mContextWeakReference;
    private RecipeItemListener mRecipeItemListener;

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private final TextView mStepsCount;
        private final TextView mTitleTv;
        private final TextView mServingsTv;
        private final ImageView mRecipeImage;
        CardView mCardView;

        public RecipeViewHolder(@NonNull CardView itemView) {
            super(itemView);
            mCardView = itemView;
            mRecipeImage = mCardView.findViewById(R.id.recipe_image);
            mTitleTv = mCardView.findViewById(R.id.card_title);
            mServingsTv = mCardView.findViewById(R.id.recipe_servings);
            mStepsCount = mCardView.findViewById(R.id.number_of_steps);
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
        CardView recipeCardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_item, parent, false);
        mContextWeakReference = new WeakReference<>(parent.getContext());
        return new RecipeViewHolder(recipeCardView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int pos) {
        bindImageById(mRecipeList.get(pos).getImage(), recipeViewHolder.mRecipeImage);
        recipeViewHolder.mTitleTv.setText(mRecipeList.get(pos).getName());
        recipeViewHolder.mServingsTv.setText(
                String.format(
                        String.valueOf(mContextWeakReference.get().getResources().getText(R.string.servings_label)),
                        mRecipeList.get(pos).getServings()));
        recipeViewHolder.mStepsCount.setText(
                String.format(
                        String.valueOf(mContextWeakReference.get().getResources().getText(R.string.number_of_steps_lable)),
                        mRecipeList.get(pos).getSteps().size())
        );
        recipeViewHolder.mCardView.setOnClickListener(view -> {
            if (mRecipeItemListener != null) {
                mRecipeItemListener.onRecipeClick(mRecipeList.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList != null ? mRecipeList.size() : 0;
    }

    public void setItemListener(RecipeItemListener listener) {
        mRecipeItemListener = listener;
    }

    public interface RecipeItemListener {
        void onRecipeClick(Recipe recipe);
    }

    private void bindImageById(String imageUri, ImageView imageView) {
        Picasso.get()
                .load(imageUri)
                .fit().centerCrop()
                .placeholder(R.drawable.ic_photo_gray_64dp)
                .error(R.drawable.ic_broken_image_gray_64dp)
                .into(imageView);
    }


}
