package com.example.astoundrushi.retrofit.rest;

import com.example.astoundrushi.retrofit.model.MovieList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Astound Rushi on 8/1/2016.
 */

public interface ApiInterface
{
    @POST("movie/top_rated")
    Call<MovieList> getTopRatedMovies(@Query("api_key") String apiKey);

    @POST("movie/{id}")
    Call<MovieList> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
