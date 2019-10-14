package com.asefactory.ase21.filereloader.di;

import android.app.Application;

import com.asefactory.ase21.filereloader.di.components.AppComponent;
import com.asefactory.ase21.filereloader.di.components.DaggerAppComponent;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getComponent(){
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .context(this)
                .build();
    }
}
