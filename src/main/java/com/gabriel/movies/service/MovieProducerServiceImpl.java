package com.gabriel.movies.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.movies.dto.MovieProducerIntervalDto;
import com.gabriel.movies.dto.MovieProducerMinMaxIntervalDto;
import com.gabriel.movies.model.MovieProducer;
import com.gabriel.movies.repository.MovieProducerRepository;

@Service
public class MovieProducerServiceImpl implements MovieProducerService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private MovieProducerRepository repository;

	@Override
	public MovieProducer create(MovieProducer producer) {
		return repository.save(producer);
	}

	@Override
	public MovieProducer findOneByName(String name) {
		List<MovieProducer> producers = repository.findByName(name);
		if (producers.size() > 1)
			throw new InternalServerErrorException("Found multiple producers with the same name.");
		return producers.stream().findFirst().orElse(null);
	}

	@Override
	public MovieProducerMinMaxIntervalDto test() {
		List<MovieProducer> producers = this.listWinners();

		List<MovieProducerIntervalDto> dtos = new ArrayList<>();

		for (MovieProducer producer : producers) {
			List<Integer> years = producer.getMovies().stream().filter(m -> m.isWinner()).map(m -> m.getYear())
					.collect(Collectors.toList());
			Collections.sort(years);

			if (years.size() > 1) {
				int i = 0;
				while (i < years.size() && (i + 1) < years.size()) {
					int minYear = years.get(i);
					int maxYear = years.get(i + 1);

					Integer interval = maxYear - minYear;

					MovieProducerIntervalDto dto = new MovieProducerIntervalDto(producer.getName(), interval, minYear,
							maxYear);
					dtos.add(dto);

					i++;
				}
			}
		}

		MovieProducerMinMaxIntervalDto minMaxDto = new MovieProducerMinMaxIntervalDto();

		int min = dtos.stream().map(d -> d.getInterval()).min(Comparator.naturalOrder()).get();
		int max = dtos.stream().map(d -> d.getInterval()).max(Comparator.naturalOrder()).get();

		minMaxDto.getMin().addAll(dtos.stream().filter(d -> d.getInterval() == min).collect(Collectors.toList()));
		minMaxDto.getMax().addAll(dtos.stream().filter(d -> d.getInterval() == max).collect(Collectors.toList()));

		return minMaxDto;
	}

	@Override
	public List<MovieProducer> listWinners() {
		return repository.findWinnerProducers();
	}
}
