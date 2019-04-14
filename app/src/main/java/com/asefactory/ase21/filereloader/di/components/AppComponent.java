package com.asefactory.ase21.filereloader.di.components;

import com.asefactory.ase21.filereloader.di.modules.AppModule;
import com.asefactory.ase21.filereloader.presentation.view.mainsettings.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
