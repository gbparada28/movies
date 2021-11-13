package com.gabriel.movies.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.gabriel.movies.dto.MovieProducerMinMaxIntervalDto;
import com.gabriel.movies.model.MovieProducer;

public interface MovieProducerService {

	public MovieProducer create(MovieProducer producer);

	public MovieProducer findOneByName(String name);

	public MovieProducerMinMaxIntervalDto test();

	public List<MovieProducer> listWinners();

	public Integer findMinYear(@Param("producerId") String producerId);

	public Integer findMaxYear(@Param("producerId") String producerId);
}
