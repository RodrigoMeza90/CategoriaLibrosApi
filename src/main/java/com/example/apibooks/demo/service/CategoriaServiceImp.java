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
import com.example.apibooks.demo.model.dao.ICategoriaDao;
import com.example.apibooks.demo.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImp implements ICategoriaService{
	
	
	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImp.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		
		log.info("inicio metodo buscarCategortia");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			List<Categoria> categoria =  (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
			
		}catch(Exception e){
			
			response.setMetadata("Respuesta noOK", "-1", "Error al consultar categoria");
			log.error("Error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		log.info("Inicio metodo buscar por id");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			}else {
				log.error("Error en consultar categoria");
				response.setMetadata("Respuesta no ok", "-1", "categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		}catch (Exception e) {
			log.error("Error en consultar categoria");
			response.setMetadata("Respuesta no ok", "-1", "error al consultar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta OK", "00", "Respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
		log.info("Inicio metodo crear categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			
			if(categoriaGuardada!=null) {
				
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			}else {
				log.error("Error en creaer categoria");
				response.setMetadata("Respuesta no ok", "-1", "categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		}catch(Exception e) {
			log.error("Error en crear categoria");
			response.setMetadata("Respuesta no ok", "-1", "error en crear categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("Respuesta OK", "00", "Categoria creada");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long id) {
		log.info("Inicio metodo actualizar categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if (categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get());
				
				if(categoriaActualizar != null) {
					response.setMetadata("Respuesta ok", "00", "categoria actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				}else {
					log.error("Error en actualizar categoria");
					response.setMetadata("Respuesta no ok", "-1", "categoria no actualizada");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
				}
			}
		
		}catch (Exception e) {
			log.error("Error en crear categoria");
			e.getStackTrace();
			response.setMetadata("Respuesta no ok", "-1", "error en actualizar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminar (Long id) {
		log.info("Inicio metodo eliminar categoria");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		
		try {
			categoriaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "categoria eliminada");
			
			
		}catch(Exception e){
			log.error("Error en eliminar categoria");
			e.getStackTrace();
			response.setMetadata("Respuesta no ok", "-1", "error en eliminar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);
	}
	
	
	

}




















