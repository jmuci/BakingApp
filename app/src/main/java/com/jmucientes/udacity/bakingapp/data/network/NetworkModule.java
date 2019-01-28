package com.jmucientes.udacity.bakingapp.data.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;

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
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
