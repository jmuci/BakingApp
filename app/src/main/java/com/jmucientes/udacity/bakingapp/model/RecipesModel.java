package com.jmucientes.udacity.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class RecipesModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private final List<Recipe> mRecipeList;
    //TODO Use ImmutableList from Guava instead !!

    public RecipesModel(List<Recipe> recipeList) {

        mRecipeList = Collections.unmodifiableList(recipeList);
    }

    protected RecipesModel(Parcel in) {
        mRecipeList = in.createTypedArrayList(Recipe.CREATOR);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mRecipeList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecipesModel> CREATOR = new Creator<RecipesModel>() {
        @Override
        public RecipesModel createFromParcel(Parcel in) {
            return new RecipesModel(in);
        }

        @Override
        public RecipesModel[] newArray(int size) {
            return new RecipesModel[size];
        }
    };

    public List<Recipe> getRecipeList() {
        return mRecipeList;
    }
}
