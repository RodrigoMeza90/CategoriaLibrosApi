package com.example.apibooks.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.apibooks.demo.model.Libro;
import com.example.apibooks.demo.response.LibroResponseRest;

public interface ILibroService {
	
	public ResponseEntity <LibroResponseRest> buscarLibro();
	public ResponseEntity <LibroResponseRest> buscarLibroPorId(Long id);
	public ResponseEntity <LibroResponseRest> guardarLibro(Libro libro);
}
