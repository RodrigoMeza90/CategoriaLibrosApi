package com.example.apibooks.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apibooks.demo.model.Libro;
import com.example.apibooks.demo.response.LibroResponseRest;
import com.example.apibooks.demo.service.ILibroService;

@RestController
@RequestMapping("/v1")
public class LibroRestController {
	
	@Autowired
	private ILibroService service;
	
	@GetMapping("/libros")
	public ResponseEntity<LibroResponseRest> consultaLibro() {
		
		ResponseEntity<LibroResponseRest> response = service.buscarLibro();
		return response;
	}
	
	@GetMapping("/libros/{id}")
	public ResponseEntity<LibroResponseRest> consultaLibroPorId(@PathVariable Long id) {
		
		ResponseEntity<LibroResponseRest> response = service.buscarLibroPorId(id);
		return response;

	}
	
	@PostMapping("/libros")
	public ResponseEntity<LibroResponseRest> guardarLibro(@RequestBody Libro request) {
		
		ResponseEntity<LibroResponseRest> response = service.guardarLibro(request);
		return response;

	}
	

}