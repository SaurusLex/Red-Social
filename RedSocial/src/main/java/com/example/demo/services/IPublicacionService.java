package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

public interface IPublicacionService {
	List<Publicacion> findAllByUsuario(Usuario usuario);
	void addPublicacion (Publicacion publicacion);
	void deletePublicacionById (int id);
}
