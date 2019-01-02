package com.jmucientes.udacity.bakingapp.di;

import com.jmucientes.udacity.bakingapp.BakingApp;
import com.jmucientes.udacity.bakingapp.data.network.NetworkModule;
import com.jmucientes.udacity.bakingapp.di.scopes.ApplicationScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        BakingAppModule.class,
        ViewModelsModule.class,
        NetworkModule.class,
        VideoPlayerModule.class,
        ActivityBindingModule.class
})
@ApplicationScope
public interface BakingAppComponent extends AndroidInjector<BakingApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        BakingAppComponent.Builder application(BakingApp bakingApp);

        BakingAppComponent build();
    }
}
