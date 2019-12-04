package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Perfil;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

public interface IUsuarioService {
	public void addUsuario(Usuario u);
	public void deleteUsuario(Usuario usuario);
//	public void updateUser(String nick);
	public ArrayList<Usuario> listar();
	public Usuario findByNick(String nick);
	public void addPublicacion(Publicacion publicacion);
	public List<Usuario> findAllFriends(String nick);
	public List<Usuario> findAllFriendOf(String nick);
	public void addFriend(Usuario usuario, Usuario amigo);
	public void removeFriend(Usuario usuario, Usuario amigo);
	public List<Usuario> getSolicitudes(String nick);
	public List<Usuario>getFriends(String nick);
	public void setPerfil(Perfil perfil, String nick);
	
	
}
