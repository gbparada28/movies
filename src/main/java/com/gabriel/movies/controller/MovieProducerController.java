package com.gabriel.movies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.movies.dto.MovieProducerMinMaxIntervalDto;
import com.gabriel.movies.service.MovieProducerService;

@RestController
@RequestMapping("/producers")
public class MovieProducerController {

	@Autowired
	private MovieProducerService service;

	@GetMapping("/getIntervals")
	public MovieProducerMinMaxIntervalDto test() {
		return service.test();
	}
}
