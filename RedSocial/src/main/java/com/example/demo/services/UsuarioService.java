package com.example.demo.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.control.Control;
import com.example.demo.model.Perfil;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.IPerfilDAO;
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
	@Autowired
	IPerfilDAO daoPerfil;

	@Override
	@Transactional
	public void addUsuario(Usuario u) {
		u.getPerfil().setUsuario(u);
		dao.save(u);

	}

	@Override
	@Transactional
	public ArrayList<Usuario> listar() {
		return (ArrayList<Usuario>) dao.findAll();
	}

	@Override
	@Transactional
	public Usuario findByNick(String nick) {
		Usuario usuario = dao.findUsuarioByNick(nick);
		if (usuario == null)
			return new Usuario();
		else
			return usuario;

	}

	@Override
	@Transactional
	public void deleteUsuario(Usuario usuario) {
		dao.delete(usuario);

	}

	@Override
	@Transactional
	public void addPublicacion(Publicacion publicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public List<Usuario> findAllFriends(String nick) {
		// TODO Auto-generated method stub
		return dao.findMisAmigosByNick(nick);
	}

	@Transactional
	@Override
	public void addFriend(Usuario usuario, Usuario amigo) {
		usuario.addFriend(amigo);
	}

	@Transactional
	@Override
	public void removeFriend(Usuario usuario, Usuario amigo) {
		usuario.removeFriend(amigo);

	}

	@Override
	public List<Usuario> findAllFriendOf(String nick) {

		return findByNick(nick).getAmigoDe();
	}

	@Override
	public List<Usuario> getSolicitudes(String nick) {
		List<Usuario> solicitudes = new ArrayList<Usuario>();
		List<Usuario> amigos = findAllFriends(nick);
		List<Usuario> soyAmigo = findAllFriendOf(nick);
		soyAmigo.forEach(us -> {
			if (!amigos.contains(us)) {
				solicitudes.add(us);
			}
		});
		return solicitudes;

	}

	public List<Usuario> getFriends(String nick) {
		List<Usuario> amigos = new ArrayList<Usuario>();
		List<Usuario> amigosHeSolicitado = findAllFriends(nick);
		List<Usuario> amigosMeHanSolicitado = findAllFriendOf(nick);

		amigosHeSolicitado.forEach(us -> {
			if (amigosMeHanSolicitado.contains(us)) {
				amigos.add(us);
			}
		});
 
		return amigos;

	}

	@Transactional
	public void setPerfil(Perfil perfil, String nick) {	
			
			Usuario us = findByNick(nick);
			perfil.setUsuario(us);
			daoPerfil.save(perfil);
			
			
			
		
		

	}

}
