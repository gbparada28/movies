package com.gabriel.movies.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gabriel.movies.dto.MovieDto;
import com.gabriel.movies.dto.MovieProducerDto;
import com.gabriel.movies.model.Movie;
import com.gabriel.movies.model.MovieProducer;
import com.gabriel.movies.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository repository;

	@Autowired
	private MovieProducerService producerService;

	@Override
	public Movie findOneByName(String name) {
		List<Movie> movies = repository.findByName(name);
		if (movies.size() > 1)
			throw new InternalServerErrorException("Found multiple movies with the same name.");
		return movies.stream().findFirst().orElse(null);
	}

	@Transactional
	public ResponseEntity<String> importMovieList(MultipartFile file) {
		String fileName = file.getOriginalFilename().toUpperCase();
		if (!fileName.endsWith(".CSV")) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Wrong file extension. Must be a CSV.");
		}
		InputStream stream;
		try {
			stream = file.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String currentLine;

			// skips headers
			reader.readLine();

			while ((currentLine = reader.readLine()) != null) {
				String[] columns = currentLine.split(";");

				if (columns.length < 4) {
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
							.body("Wrong file format. Ensure it's a semicolon separated csv with at least 5 columns");
				}

				int year = Integer.parseInt(columns[0]);
				String movieName = columns[1].trim();
				String studioName = columns[2].trim();
				String producerString = columns[3].replaceAll(",and", "+").replaceAll(", and", "+").replaceAll(",",
						"+");
				producerString = replaceLast(producerString, " and ", "+");
				List<String> producerNames = Arrays.asList(producerString.split("\\+"));

				boolean winner = columns.length > 4 ? "yes".equalsIgnoreCase(columns[4].trim()) : false;

				// in case of lists with repeated movies
				Movie movie = findOneByName(movieName);
				if (movie == null) {
					movie = new Movie(year, movieName, studioName, winner);
					repository.save(movie);
				}

				StringJoiner joiner = new StringJoiner(", ");
				for (String producerName : producerNames) {

					if (producerName.trim().isEmpty()) {
						continue;
					}

					joiner.add(producerName.trim());

					MovieProducer producer = producerService.findOneByName(producerName.trim());

					if (producer == null) {
						producer = producerService.create(new MovieProducer(producerName.trim()));
					}

					if (!producer.getMovies().contains(movie)) {
						producer.getMovies().add(movie);
					}

					if (!movie.getProducers().contains(producer)) {
						movie.getProducers().add(producer);
					}

				}

				repository.save(movie);
			}

			return ResponseEntity.status(HttpStatus.OK).body("Success.");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Error reading file.");
		}
	}

	private String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
	}

	@Override
	public List<MovieDto> list() {
		return toCollectionDto(this.repository.findAll());
	}

	@Override
	public List<Movie> listWinners() {
		return repository.findWinnerMovies();
	}

	private List<MovieDto> toCollectionDto(List<Movie> movies) {
		return movies.stream().map(m -> toDto(m)).collect(Collectors.toList());
	}

	private MovieDto toDto(Movie movie) {
		MovieDto dto = new MovieDto();
		dto.setId(movie.getId());
		dto.setName(movie.getName());
		dto.setStudio(movie.getStudio());
		dto.setWinner(movie.isWinner());
		dto.setProducers(toProducerCollectionDto(movie.getProducers()));
		return dto;
	}

	private List<MovieProducerDto> toProducerCollectionDto(List<MovieProducer> producers) {
		return producers.stream().map(p -> toDto(p)).collect(Collectors.toList());
	}

	private MovieProducerDto toDto(MovieProducer producer) {
		MovieProducerDto dto = new MovieProducerDto();
		dto.setId(producer.getId());
		dto.setName(producer.getName());
		return dto;
	}

}
