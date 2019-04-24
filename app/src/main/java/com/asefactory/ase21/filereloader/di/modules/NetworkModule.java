package com.asefactory.ase21.filereloader.di.modules;

import com.asefactory.ase21.filereloader.data.network.DownloadAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    private final String BASE_URL = "http://google.com/";

    @Singleton
    @Provides
    DownloadAPI provideDownloadAPI(Retrofit retrofit){
        return retrofit.create(DownloadAPI.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
    }

}
