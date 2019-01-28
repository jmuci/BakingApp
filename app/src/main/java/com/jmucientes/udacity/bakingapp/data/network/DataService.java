package com.jmucientes.udacity.bakingapp.data.network;

import com.jmucientes.udacity.bakingapp.model.Recipe;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Single<List<Recipe>> getAllRecipesRx();

    //TODO Remove this one once all request logic moved to RX
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getAllRecipes();
}
