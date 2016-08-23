package com.example.astoundrushi.retrofit.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Astound Rushi on 8/1/2016.
 */

public class ApiClient
{
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit adapter = null;

    public static Retrofit getClient()
    {
        if (adapter == null)
        {
            adapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return adapter;
    }
}
