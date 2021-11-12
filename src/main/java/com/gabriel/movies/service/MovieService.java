package com.gabriel.movies.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.movies.model.Movie;

public interface MovieService {
	public ResponseEntity<String> importMovieList(MultipartFile file);

	public Movie findOneByName(String name);

	public List<Movie> list();

	public List<Movie> listWinners();
}
