package com.example.astoundrushi.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Astound Rushi on 8/1/2016.
 */

public class MovieList
{
    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("results")
    private List<MovieDetails> Results;

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public int getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(int totalResults)
    {
        this.totalResults = totalResults;
    }

    public List<MovieDetails> getResults()
    {
        return Results;
    }

    public void setResults(List<MovieDetails> results)
    {
        Results = results;
    }
}
