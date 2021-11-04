package com.gabriel.movies.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.gabriel.movies.controller.MoviesResource;

@Component
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		  register(MoviesResource.class);
	}

}
