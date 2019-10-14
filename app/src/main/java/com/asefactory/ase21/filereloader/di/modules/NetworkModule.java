package com.asefactory.ase21.filereloader.di.modules;

import com.asefactory.ase21.filereloader.data.network.DownloadAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    DownloadAPI provideDownloadAPI(Retrofit retrofit){
        return retrofit.create(DownloadAPI.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(){

        String BASE_URL = "http://google.com/";

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    private HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
