package com.jmucientes.udacity.bakingapp.main.di;

import com.jmucientes.udacity.bakingapp.main.BakingApp;
import com.jmucientes.udacity.bakingapp.main.di.scopes.ApplicationScope;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        AndroidSupportInjectionModule.class,
        BakingAppModule.class,
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
