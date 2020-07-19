package com.movie.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.MovieEntity;
import com.movie.model.Movie;
import com.movie.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRepo;
	
	@Override
	public Movie saveMovie(Movie movie) {
		MovieEntity movieEntity = new MovieEntity();
		BeanUtils.copyProperties(movie, movieEntity);
		MovieEntity saveEntity = movieRepo.save(movieEntity);
		BeanUtils.copyProperties(saveEntity, movie);
		return movie;
		
	}
	@Override
	public List<Movie> getAllMovies() {
		List<MovieEntity> findAll = movieRepo.findAll();

		return findAll.stream().map(movieEntity -> {
			Movie movie = new Movie();
			BeanUtils.copyProperties(movieEntity, movie);
			return movie;
		}).collect(Collectors.toList()).stream()
				.collect(Collectors.toList());

	}
	
	@Override
	public Movie getMovieByID(Long id) {
		
		Optional<MovieEntity> findById = movieRepo.findById(id);
		if(findById.isPresent()) {
			MovieEntity movieEntity = findById.get();
	
			Movie movie = new Movie();
		
			BeanUtils.copyProperties(movieEntity, movie);
			return movie;
		}
		return null;
	}
	
	@Override
	public void deleteMovieByID(Long id) {
		movieRepo.deleteById(id);
		
	}

}
