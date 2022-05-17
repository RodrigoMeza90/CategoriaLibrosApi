package com.example.apibooks.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.apibooks.demo.model.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria, Long> {

}
