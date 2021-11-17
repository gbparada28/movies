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

	public boolean equals(MovieProducerMinMaxIntervalDto other) {
		if (this.getMin().size() == other.getMin().size() && this.getMax().size() == other.getMax().size()) {
			int i = 0;
			while (i < this.getMin().size()) {
				if (!this.getMin().get(i).equals(other.getMin().get(i)))
					return false;
				i++;
			}

			i = 0;
			while (i < this.getMax().size()) {
				if (!this.getMax().get(i).equals(other.getMax().get(i)))
					return false;
				i++;
			}

			return true;
		}

		return false;
	}

}
