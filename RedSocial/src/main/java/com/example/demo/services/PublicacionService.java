package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.IPublicacionDAO;

@Service
public class PublicacionService implements IPublicacionService {

	@Autowired
	IPublicacionDAO dao;
	
	@Override
	public List<Publicacion> findAllByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return dao.findAllByUsuario(usuario);
	}
	
	@Override
	public void addPublicacion (Publicacion publicacion) {
		try {
			dao.save(publicacion);
		} catch (EntityExistsException e) {
			// TODO: handle exception
		}
	}

	@Override
	public void deletePublicacionById(int id) {
		dao.deleteById(id);
		
	}

	

	

	

}
