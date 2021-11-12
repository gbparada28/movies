package com.gabriel.movies.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "movieproducer")
public class MovieProducer extends BaseEntity {
	@Column(length = 1024)
	@NotNull
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Movie> movies = new ArrayList<>();

	public MovieProducer() {
		super();
	}

	public MovieProducer(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
