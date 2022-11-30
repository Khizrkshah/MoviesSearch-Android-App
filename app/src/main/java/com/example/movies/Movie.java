package com.example.movies;

public class Movie {
    String title;
    String year;
    String posterUrl;
    String imdbId;
    String director;
    String actor;

    Movie(String title,String year, String posterUrl, String imdbId){
        this.title = title;
        this.year = year;
        this.posterUrl = posterUrl;
        this.imdbId = imdbId;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setActor(String actors){
        this.actor = actors;
    }

    public String getDirector(){
        return director;
    }

    public String getActor(){
        return actor;
    }
}
