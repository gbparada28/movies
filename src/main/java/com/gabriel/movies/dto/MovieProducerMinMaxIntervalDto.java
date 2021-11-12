package com.gabriel.movies.dto;

import java.util.ArrayList;
import java.util.List;

public class MovieProducerMinMaxIntervalDto {

	private List<MovieProducerIntervalDto> min = new ArrayList<>();
	private List<MovieProducerIntervalDto> max = new ArrayList<>();

	public MovieProducerMinMaxIntervalDto() {

	}

	public List<MovieProducerIntervalDto> getMin() {
		return min;
	}

	public void setMin(List<MovieProducerIntervalDto> min) {
		this.min = min;
	}

	public List<MovieProducerIntervalDto> getMax() {
		return max;
	}

	public void setMax(List<MovieProducerIntervalDto> max) {
		this.max = max;
	}

}
