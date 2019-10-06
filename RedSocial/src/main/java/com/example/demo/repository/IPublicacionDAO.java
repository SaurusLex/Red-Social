package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

@Repository
public interface IPublicacionDAO extends JpaRepository<Publicacion, Integer>, CrudRepository<Publicacion, Integer> {

	List<Publicacion> findAllByUsuario(Usuario usuario);
	
}
