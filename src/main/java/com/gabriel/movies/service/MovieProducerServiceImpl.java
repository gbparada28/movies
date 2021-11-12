package com.gabriel.movies.service;

import java.util.ArrayList;
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

	public List<MovieProducer> list() {
		return repository.findAll();
	}

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

		Integer minInterval = null;
		Integer maxInterval = null;

		for (MovieProducer producer : producers) {
			Integer minYear = this.findMinYear(producer.getId());
			Integer maxYear = this.findMaxYear(producer.getId());

			if (minYear != null && maxYear != null) {
				Integer interval = maxYear - minYear;

				if (interval > 0) {

					if (minInterval == null || interval < minInterval) {
						minInterval = interval;
					}
					if (maxInterval == null || interval > maxInterval) {
						maxInterval = interval;
					}

					MovieProducerIntervalDto dto = new MovieProducerIntervalDto(producer.getName(), interval, minYear,
							maxYear);
					dtos.add(dto);
				}
			}
		}

		MovieProducerMinMaxIntervalDto minMaxDto = new MovieProducerMinMaxIntervalDto();

		final int fMin = minInterval != null ? minInterval.intValue() : 0;
		final int fMax = maxInterval != null ? maxInterval.intValue() : 0;

		minMaxDto.getMin().addAll(dtos.stream().filter(d -> fMin == d.getInterval()).collect(Collectors.toList()));
		minMaxDto.getMax().addAll(dtos.stream().filter(d -> fMax == d.getInterval()).collect(Collectors.toList()));

		return minMaxDto;
	}

	@Override
	public List<MovieProducer> listWinners() {
		return repository.findWinnerProducers();
	}

	@Override
	public Integer findMinYear(String producerId) {
		return repository.findMinYear(producerId).stream().findFirst().orElse(null);
	}

	@Override
	public Integer findMaxYear(String producerId) {
		return repository.findMaxYear(producerId).stream().findFirst().orElse(null);
	}
}
