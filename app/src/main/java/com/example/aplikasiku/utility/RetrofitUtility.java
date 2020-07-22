package com.example.aplikasiku.utility;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtility {
    public static Retrofit initialieRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client =new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Const.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return  retrofit;
    }
}


