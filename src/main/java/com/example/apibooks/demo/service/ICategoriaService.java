package com.example.apibooks.demo.service;


import org.springframework.http.ResponseEntity;

import com.example.apibooks.demo.model.Categoria;
import com.example.apibooks.demo.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
	
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
	
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id);
	
	public ResponseEntity<CategoriaResponseRest> eliminar(Long id);

}
