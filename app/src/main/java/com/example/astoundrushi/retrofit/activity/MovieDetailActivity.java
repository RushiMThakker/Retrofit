package com.example.astoundrushi.retrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.astoundrushi.retrofit.R;
import com.example.astoundrushi.retrofit.model.MovieDetails;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;

public class MovieDetailActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initUI();
        App.initImageLoader(getBaseContext());
    }

    void initUI()
    {
        TextView movieTitle = (TextView) findViewById(R.id.txtMovieTitle);
        TextView movieOverView = (TextView) findViewById(R.id.etMovieOverView);
        ImageView movieImage = (ImageView) findViewById(R.id.imgMovieImage);
        TextView moviePopularityValue = (TextView) findViewById(R.id.txtPopularityValue);
        TextView movieAverageValue = (TextView) findViewById(R.id.txtMovieAverageValue);
        RatingBar moviePopularityRatingBar = (RatingBar) findViewById(R.id.rbPopularity);
        RatingBar movieAverageRatingBar = (RatingBar) findViewById(R.id.rbAverage);
        Double moviePopularityRating;
        Double movieAverageRating;
        StringBuffer imageURL=new StringBuffer("https://image.tmdb.org/t/p/w300");

        Intent movieReceiveIntent=getIntent();
        MovieDetails receivedMovie=(MovieDetails)movieReceiveIntent.getSerializableExtra("movie");

        imageURL.append(receivedMovie.getPosterPath());
        movieTitle.setText(receivedMovie.getTitle());
        ImageLoader.getInstance().displayImage(imageURL.toString(),movieImage);
        movieOverView.setText(receivedMovie.getOverview());
        DecimalFormat df=new DecimalFormat("#.##");
        moviePopularityValue.setText(df.format(receivedMovie.getPopularity()).toString());
        movieAverageValue.setText(df.format(receivedMovie.getVoteAverage()).toString());

        moviePopularityRating=Double.valueOf(receivedMovie.getPopularity()/10);
        movieAverageRating=Double.valueOf(receivedMovie.getVoteAverage()/10);
        moviePopularityRatingBar.setRating(moviePopularityRating.floatValue());
        movieAverageRatingBar.setRating(movieAverageRating.floatValue());
    }
}
