package com.movie.service;

import java.util.List;

import com.movie.model.Movie;


public interface MovieService {
	
	public Movie saveMovie(Movie movie);
	public List<Movie> getAllMovies();
	public Movie getMovieByID(Long id);
	public void deleteMovieByID(Long id);


}
