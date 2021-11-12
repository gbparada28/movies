package com.gabriel.movies.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(length = 32, updatable = false)
		private String id;

	    public BaseEntity() {
	    	id = java.util.UUID.randomUUID().toString().replaceAll("-", "");
	    }
	 
	    public String getId() {
	        return id;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
}
