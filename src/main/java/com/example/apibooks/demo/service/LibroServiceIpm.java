package com.example.apibooks.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.apibooks.demo.model.Libro;
import com.example.apibooks.demo.model.dao.ILibroDao;
import com.example.apibooks.demo.response.LibroResponseRest;

@Service
public class LibroServiceIpm implements ILibroService {
	
	private static final Logger log = LoggerFactory.getLogger(LibroServiceIpm.class);
	
	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> buscarLibro() {
		
		log.info("inicio metodo buscarLibro");
		
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			List<Libro> libro =  (List<Libro>) libroDao.findAll();
			
			response.getLibroResponse().setLibro(libro);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			
		}catch(Exception e){
			
			response.setMetadata("Respuesta noOK", "-1", "Error al consultar libro");
			log.error("Error al consultar libro: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
		
	}
	
	
	
	

}
