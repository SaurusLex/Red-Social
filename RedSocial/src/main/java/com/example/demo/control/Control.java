package com.example.demo.control;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Perfil;
import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

import com.example.demo.services.IPublicacionService;
import com.example.demo.services.IUsuarioService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.*;



import org.slf4j.*;

@Slf4j
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@Transactional
@RestController
public class Control {

	@Autowired
	IUsuarioService servicio;
	@Autowired
	IPublicacionService servicioPublicacion;



	// Registra un usuario nuevo
	@PostMapping("/registro")
	public void registro(@RequestBody(required = false) Usuario usuario) {
		servicio.addUsuario(usuario);
		log.info("Usuario añadido con exito desde front");
		
	}

	// Obtiene la lista de usuarios
	@GetMapping("/users")
	public ArrayList<Usuario> usuarios() {
		return servicio.listar();
	}

	// Obtiene un usuario a partir de un nick
	@GetMapping("/user/{nick}")
	public Usuario getUsuario(@PathVariable String nick) {
		log.info("login");
		return servicio.findByNick(nick);
	}

	// Elimina un usuario segun su nick
	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping("/user/{nick}")
	public void deleteUsuario(@PathVariable String nick) {
		Usuario us = servicio.findByNick(nick);
		servicio.deleteUsuario(us);
	}
	
	@GetMapping(value="/getImage")
	public byte[] getImage() throws IOException {
		BufferedImage bImage = ImageIO.read(new File("C:\\Users\\WalterBlack\\Desktop\\foto.jpg"));
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "jpg", bos );
	      byte [] data = bos.toByteArray();
	      System.out.println(Arrays.toString(data));
	      return data;
	}
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/publicaciones/{nick}")
	public List<Publicacion> publicar(@PathVariable String nick) {
		return servicioPublicacion.findAllByUsuario(servicio.findByNick(nick));
		
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("/publicacion")
	public void registro(@RequestBody Publicacion publicacion) {
		publicacion.setFecha(LocalDateTime.now());
		servicioPublicacion.addPublicacion(publicacion);
		log.info("Publicacion añadida desde el front");
		
	}
	
	@DeleteMapping("/publicacion/{id}")
	public void deletePublicacion(@PathVariable int id) {
		servicioPublicacion.deletePublicacionById(id);
		log.info("Publicacion borrada desde el front");
	}
	
//	@GetMapping("/friends/{nick}")
//	public List<Usuario> findAllFriends(@PathVariable String nick){
//		return servicio.findAllFriends(nick);
//	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("/friends/add/{nick}")
	public void addFriend(@PathVariable(name = "nick") String nick, @RequestBody(required = true) Usuario usuario) {
		servicio.addFriend(servicio.findByNick(nick), usuario );
		
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@PostMapping("/friends/delete/{nick}")
	public void deleteFriend(@PathVariable(name = "nick") String nick, @RequestBody(required = true) Usuario usuario) {
		System.out.println("voy a borrar un amigo");
		servicio.removeFriend(servicio.findByNick(nick), usuario );
		
	}
	
//	@GetMapping("/friendof/{nick}")
//	public List<Usuario> findAllFriendOf(@PathVariable String nick){
//		return servicio.findAllFriendOf(nick);
//	}
	
	@GetMapping("/solicitudes/{nick}")
	public List<Usuario> getSolicitudes(@PathVariable String nick){
		return servicio.getSolicitudes(nick);
	}
	
	@GetMapping("/friends/{nick}")
	public List<Usuario> getFriends(@PathVariable String nick){
		return servicio.getFriends(nick);
	}
	
	@PostMapping("/perfil/{nick}")
	public void setPerfil(@PathVariable String nick, @RequestBody Perfil perfil) {
		servicio.setPerfil(perfil, nick);
	}
	
	@GetMapping("/perfil/{nick}")
	public Perfil getPerfil(@PathVariable String nick) {
		return servicio.findByNick(nick).getPerfil();
	}
	
	@GetMapping("/solicitados/{nick}")
	public List<Usuario> getSolicitados(@PathVariable String nick){
		return servicio.findAllFriends(nick);
	}


}
