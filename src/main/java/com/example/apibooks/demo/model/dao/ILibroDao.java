package com.example.apibooks.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.apibooks.demo.model.Libro;

public interface ILibroDao extends CrudRepository <Libro ,Long >{
	

}
