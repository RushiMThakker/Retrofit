package com.example.astoundrushi.retrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.astoundrushi.retrofit.R;
import com.example.astoundrushi.retrofit.model.Movie;
import com.example.astoundrushi.retrofit.model.MovieResponse;
import com.example.astoundrushi.retrofit.rest.ApiClient;
import com.example.astoundrushi.retrofit.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "bc849f4578023ca7ac6ef809d49cde9a";
    public interface ApiInterface
    {
        @POST("movie/top_rated")
        Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);

        @POST("movie/{id}")
        Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }


        ApiInterface apiService = retrofit.create(ApiInterface.class);

        Call<Movie> call = .getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<Movie>()
        {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                List<MovieResponse> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}