package com.jmucientes.udacity.bakingapp.data.network;

import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DataService {

    @GET("bins/1bul8q")
    Single<List<Recipe>> getAllRecipesRx();
}
