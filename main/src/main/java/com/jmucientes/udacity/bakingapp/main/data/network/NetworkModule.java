package com.jmucientes.udacity.bakingapp.main.data.network;

import com.jmucientes.udacity.bakingapp.main.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

    @Provides
    @ApplicationScope
    public static Retrofit provideRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public static DataService providesDataService(Retrofit retrofit) {
        return retrofit.create(DataService.class);
    }

}
