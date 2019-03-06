package com.made.fajaregafirmansyah.rest;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //membuat variabel dengan nama BASE_URL yang isinya link URL utama untuk mengakses API
    private static final String BASE_URL = "https://api.themoviedb.org";

    //membuat variabel dengan nama retrofit set awal nilainya null
    private static Retrofit retrofit = null;

    //konversi ke bentuk obyek
    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
