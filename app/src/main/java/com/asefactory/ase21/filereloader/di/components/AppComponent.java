package com.asefactory.ase21.filereloader.di.components;

import com.asefactory.ase21.filereloader.di.modules.AppModule;
import com.asefactory.ase21.filereloader.di.modules.NetworkModule;
import com.asefactory.ase21.filereloader.di.modules.PrefsModule;
import com.asefactory.ase21.filereloader.presentation.view.bootreceiver.BootReceiver;
import com.asefactory.ase21.filereloader.presentation.view.downloadreceiver.DownloadBrodcastReceiver;
import com.asefactory.ase21.filereloader.presentation.view.mainsettings.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
