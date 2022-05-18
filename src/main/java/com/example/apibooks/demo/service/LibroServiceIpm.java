package com.example.apibooks.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.apibooks.demo.model.Categoria;
import com.example.apibooks.demo.model.Libro;
import com.example.apibooks.demo.model.dao.ILibroDao;
import com.example.apibooks.demo.response.CategoriaResponseRest;
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

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<LibroResponseRest> buscarLibroPorId(Long id) {
		
		log.info("inicio metodo buscarLibroPorId");
		LibroResponseRest response = new LibroResponseRest();
		List <Libro> list = new ArrayList<>();
		
		try {
		
			Optional<Libro> libro = libroDao.findById(id);
			
			if(libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			}else {
				log.error("Error en consultar libro");
				response.setMetadata("Respuesta no ok", "-1", "libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch(Exception e)
		{
			log.error("Error en consultar libro");
			response.setMetadata("Respuesta no ok", "-1", "error al consultar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
		
		
	}

	@Override
	public ResponseEntity<LibroResponseRest> guardarLibro(Libro libro) {
		
		log.info("Inicio metodo guardarLibro");
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Libro libroGuardado = libroDao.save(libro);
			
			if(libroGuardado!=null) {
				
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
			}else {
				log.error("Error en guardar libro");
				response.setMetadata("Respuesta no ok", "-1", "libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		}catch(Exception e) {
			log.error("Error en crear categoria");
			response.setMetadata("Respuesta no ok", "-1", "error en guardar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta OK", "00", "Libro guardado");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);
	}
	
	
	
	

}
