package com.example.apibooks.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.apibooks.demo.response.LibroResponseRest;

public interface ILibroService {
	
	public ResponseEntity <LibroResponseRest> buscarLibro();

}
