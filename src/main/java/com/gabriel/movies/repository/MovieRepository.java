package com.gabriel.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriel.movies.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m WHERE m.name = :name")
	List<Movie> findByName(@Param("name") String name);

	@Query("SELECT m FROM Movie m WHERE m.winner = true ORDER BY m.year")
	List<Movie> findWinnerMovies();
}
