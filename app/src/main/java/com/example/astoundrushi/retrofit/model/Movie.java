package com.example.astoundrushi.retrofit.model;

import java.util.List;

/**
 * Created by Astound Rushi on 8/1/2016.
 */

public class Movie
{
    int page,totalPages,totalResults;
    List<MovieResponse> Results;

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

    public List<MovieResponse> getResults()
    {
        return Results;
    }

    public void setResults(List<MovieResponse> results)
    {
        Results = results;
    }
}
