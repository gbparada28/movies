package com.gabriel.movies.service;

import java.util.List;

import com.gabriel.movies.dto.MovieProducerMinMaxIntervalDto;
import com.gabriel.movies.model.MovieProducer;

public interface MovieProducerService {

	public MovieProducer create(MovieProducer producer);

	public MovieProducer findOneByName(String name);

	public MovieProducerMinMaxIntervalDto test();

	public List<MovieProducer> listWinners();
}
