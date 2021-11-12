package com.gabriel.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriel.movies.model.MovieProducer;

@Repository
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {

	@Query("SELECT p FROM MovieProducer p WHERE p.name = :name")
	List<MovieProducer> findByName(@Param("name") String name);

	@Query("SELECT DISTINCT p FROM MovieProducer p INNER JOIN p.movies m WHERE m.winner = true")
	List<MovieProducer> findWinnerProducers();

	@Query("SELECT m.year FROM MovieProducer p INNER JOIN p.movies m WHERE m.winner = true AND p.id = :producerId ORDER BY m.year ASC")
	List<Integer> findMinYear(@Param("producerId") String producerId);

	@Query("SELECT m.year FROM MovieProducer p INNER JOIN p.movies m WHERE m.winner = true AND p.id = :producerId ORDER BY m.year DESC")
	List<Integer> findMaxYear(@Param("producerId") String producerId);
}
