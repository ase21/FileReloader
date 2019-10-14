package com.asefactory.ase21.filereloader.di.components;

import android.content.Context;

import com.asefactory.ase21.filereloader.di.modules.NetworkModule;
import com.asefactory.ase21.filereloader.di.modules.PrefsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {PrefsModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder context(Context context);

        AppComponent build();
    }
}
