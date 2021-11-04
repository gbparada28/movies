package com.gabriel.movies.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/movies")
public class MoviesResource {
	
	@GET
	@Path("/test")
	public String getTestString() {
		return "test";
	}
	
}
