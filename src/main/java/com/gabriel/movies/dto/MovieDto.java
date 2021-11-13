package com.gabriel.movies.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieDto {
	private String id;
	private String name;
	private String studio;
	private int year;
	private List<MovieProducerDto> producers = new ArrayList<>();
	private boolean winner = false;

	public MovieDto() {

	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<MovieProducerDto> getProducers() {
		return producers;
	}

	public void setProducers(List<MovieProducerDto> producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

}
