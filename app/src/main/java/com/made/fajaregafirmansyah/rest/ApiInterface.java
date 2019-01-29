package com.made.fajaregafirmansyah.rest;

import com.made.fajaregafirmansyah.BuildConfig;
import com.made.fajaregafirmansyah.model.ItemsMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //endpoint untuk pencarian film di https://developers.themoviedb.org/3/search/search-movies
    @GET("/3/search/movie?api_key=" + BuildConfig.API_KEY + "&language=en-US&")
    Call<ItemsMovie> ambilItemFilm(
            @Query("query") String nama_film
    );

    @GET("/3/movie/upcoming?api_key=" + BuildConfig.API_KEY + "&language=en-US&")
    Call<ItemsMovie> ambilItemFilmMendatang();
}
