package com.movie.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "MOVIE_DTLS")
public class MovieEntity {
	
	@Id
	@SequenceGenerator(name = "mid_seq_gen", sequenceName = "MOVIE_ID_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "mid_seq_gen", strategy = GenerationType.SEQUENCE)
	private Long movie_id;
	
	private String movie_name;
	private String movie_director;
	private String movie_hero;

}
