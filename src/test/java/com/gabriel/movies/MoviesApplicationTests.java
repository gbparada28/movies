package com.gabriel.movies;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.gabriel.movies.dto.MovieDto;
import com.gabriel.movies.dto.MovieProducerIntervalDto;
import com.gabriel.movies.dto.MovieProducerMinMaxIntervalDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoviesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class MoviesApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	private String getFilesDir() {
		return "src/main/resources/files";
	}

	@Test
	@Order(1)
	public void testImportMovies() throws IOException {
		File file = new File(getFilesDir() + "/movielist.csv");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(file));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = restTemplate.postForEntity(getRootUrl() + "/movies/import/", requestEntity,
				String.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(response.getBody().equals("Success."));
	}

	@Test
	@Order(2)
	public void testMoviesListAfterImport() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<MovieDto[]> response = restTemplate.exchange(getRootUrl() + "/movies/", HttpMethod.GET, entity,
				MovieDto[].class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(response.getBody().length == 206);
	}

	@Test
	@Order(3)
	public void testProducersIntervalRequest() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<MovieProducerMinMaxIntervalDto> response = restTemplate.exchange(
				getRootUrl() + "/producers/getIntervals", HttpMethod.GET, entity, MovieProducerMinMaxIntervalDto.class);

		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertNotNull(response.getBody());

		MovieProducerMinMaxIntervalDto dto = new MovieProducerMinMaxIntervalDto();
		dto.getMax().add(new MovieProducerIntervalDto("Matthew Vaughn", 13, 2002, 2015));
		dto.getMin().add(new MovieProducerIntervalDto("Joel Silver", 1, 1990, 1991));

		assertTrue(response.getBody().equals(dto));
	}

}
