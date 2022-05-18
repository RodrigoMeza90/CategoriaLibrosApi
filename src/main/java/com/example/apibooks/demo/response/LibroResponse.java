package com.example.apibooks.demo.response;

import java.util.List;

import com.example.apibooks.demo.model.Libro;

public class LibroResponse {
	
	public List <Libro> libro;

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	
	

}
