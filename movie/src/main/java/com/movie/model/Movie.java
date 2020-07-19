package com.movie.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Movie {
	
	private Long movie_id;
	 @Size(min=4,max=10)
	 private String movie_name;
	 @Email
	 private String movie_director;
	 @NotNull
	 private String movie_hero;

}
