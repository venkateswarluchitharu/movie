package com.movie.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.movie.exception.UserNotFoundException;
import com.movie.model.Movie;
import com.movie.service.MovieServiceImpl;

@RestController
@RequestMapping("/Movie")
public class MovieController {
	
	@Autowired
	MovieServiceImpl movieServiceImpl;
	
	@PostMapping("/save")
	public  ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
		Movie savedMovie = movieServiceImpl.saveMovie(movie);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().pathSegment("/{id}")
	.buildAndExpand(savedMovie.getMovie_id()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Movie>> getAllMovie() {
		
		List<Movie> movies= movieServiceImpl.getAllMovies();
		if(movies.isEmpty()) {
			throw new UserNotFoundException("no contacts are there!");
		}
		else {
		
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.FOUND);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Movie> getMovieByID(@PathVariable("id") long id) {
		
		Movie movieFindByID= movieServiceImpl.getMovieByID(id);
		if(movieFindByID==null) {
			throw new UserNotFoundException(id+" contact not found");
		}
		else {
		return new ResponseEntity<Movie>(movieFindByID, HttpStatus.FOUND);
		}
	}
	
	@PutMapping("/get/{uid}") 
	  public ResponseEntity<Movie> updateMovie(@PathVariable("mid") Long uid, @RequestBody Movie movieinfo)
	  { 
	  Movie movie = movieServiceImpl.getMovieByID(uid);
	  BeanUtils.copyProperties(movieinfo, movie); 
	  movieServiceImpl.saveMovie(movie);
	  //return
	   URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().build()
				.toUri();
	  	return	ResponseEntity.created(location).build();
	  
	  }
	
	  @DeleteMapping("/get/{mid}")
			public ResponseEntity<Movie> deleteUser(@PathVariable("mid") Long mid) {
				Movie movieFindByID= movieServiceImpl.getMovieByID(mid);
			
				if(movieFindByID==null) {
					throw new UserNotFoundException(mid+" contact not found");
				}
				else {
					movieServiceImpl.deleteMovieByID(mid);
				return new ResponseEntity<Movie>(HttpStatus.OK);
				}
			}

}
