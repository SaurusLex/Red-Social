package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.control.Control;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.IPublicacionDAO;
import com.example.demo.repository.IUsuarioDAO;
import com.google.common.collect.*;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioService implements IUsuarioService {

	
	
	@Autowired
	IUsuarioDAO dao;

	@Override
	public void addUsuario(Usuario u) {
		dao.save(u);

	}

	@Override
	public ArrayList<Usuario> listar() {
		return (ArrayList<Usuario>) dao.findAll();
	}

	@Override
	public Usuario findByNick(String nick) {
		Usuario usuario = dao.findUsuarioByNick(nick);
		if (usuario == null)
			return new Usuario();
		else
			return usuario;

	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		dao.delete(usuario);

	}

	@Override
	public void addPublicacion(Publicacion publicacion) {
		// TODO Auto-generated method stub
		
	}

//	public void addPublicacion(Publicacion publicacion) {
//		Usuario us = findByNick("alex");
//		log.info(us.toString());
//		us.publicaciones.add(publicacion);
//		log.info(us.toString());
//		dao.saveAndFlush(us);
//	}

}
