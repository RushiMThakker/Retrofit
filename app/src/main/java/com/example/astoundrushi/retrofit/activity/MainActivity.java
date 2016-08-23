package com.example.astoundrushi.retrofit.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.astoundrushi.retrofit.R;
import com.example.astoundrushi.retrofit.model.MovieList;
import com.example.astoundrushi.retrofit.model.MovieDetails;
import com.example.astoundrushi.retrofit.rest.ApiClient;
import com.example.astoundrushi.retrofit.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements image.OnFragmentInteractionListener
{
    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "bc849f4578023ca7ac6ef809d49cde9a";
    private ViewPager topMoviesPager;
    private List<MovieDetails> movies;
    private MyPagerAdapter myPagerAdapter;

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


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieList> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieList>()
        {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response)
            {
                movies = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + movies.size());
                topMoviesPager = (ViewPager) findViewById(R.id.moviePager);
                myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
                topMoviesPager.setAdapter(myPagerAdapter);
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t)
            {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    public class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            StringBuffer imageUrl = new StringBuffer("https://image.tmdb.org/t/p/w300");
            imageUrl.append(movies.get(position).getPosterPath().toString());
            return image.newInstance(imageUrl.toString(),movies.get(position));
        }

        @Override
        public int getCount()
        {
            return 10;
        }
    }

}