package com.gabriel.movies.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@SuppressWarnings("serial")
@Entity
public class Movie extends BaseEntity {
	@Column(length = 1024)
	@NotNull
	private String name;

	@Column(length = 1024)
	@NotNull
	private String studio;

	@NotNull
	private int year;

	@Cascade(CascadeType.SAVE_UPDATE)
	@ManyToMany(fetch = FetchType.LAZY)
	private List<MovieProducer> producers = new ArrayList<>();

	private boolean winner = false;

	public Movie() {
		super();
	}

	public Movie(int year, String name, String studio, boolean winner) {
		super();
		this.year = year;
		this.name = name;
		this.studio = studio;
		this.winner = winner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<MovieProducer> getProducers() {
		return producers;
	}

	public void setProducers(List<MovieProducer> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
}
