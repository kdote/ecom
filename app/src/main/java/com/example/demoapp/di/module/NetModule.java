package com.example.demoapp.di.module;

import com.example.demoapp.network.ApiInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = AppModule.class)
public class NetModule {
    String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public ApiInterface provideApiService(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

}
