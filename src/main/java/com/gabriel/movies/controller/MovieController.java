package com.gabriel.movies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.movies.model.Movie;
import com.gabriel.movies.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	@Autowired
	private MovieService service;

	@GetMapping("/")
	public List<Movie> listMovies() {
		return service.list();
	}

	@PostMapping("/import")
	public ResponseEntity<String> readMoviesFile(@RequestParam("file") MultipartFile file) {
		return service.importMovieList(file);
	}
}
