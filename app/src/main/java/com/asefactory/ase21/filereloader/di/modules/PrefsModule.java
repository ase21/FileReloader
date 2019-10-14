package com.asefactory.ase21.filereloader.di.modules;

import android.content.Context;

import com.asefactory.ase21.filereloader.data.shared_prefs.SharePref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {

    @Singleton
    @Provides
    SharePref provideSharePref(Context context){
        return new SharePref(context);
    }
}
