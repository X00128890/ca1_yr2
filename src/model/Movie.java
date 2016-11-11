/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import javax.persistence.*;
/**
 *
 * @author T450
 */
public class Movie {
    private int movieID;
    private String movieName;
    private String time;
//    private String category;

    public Movie(){
        
    }
    
    public Movie(int movieID, String movieName, String time) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.time = time;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getTime() {
        return time;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
    
}
