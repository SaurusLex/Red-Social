package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {

	Usuario findUsuarioByNick(@NotNull String nick);
	@Query("SELECT misAmigos FROM Usuario u where u.nick=:nick")
	List<Usuario> findMisAmigosByNick(String nick);
	
	
	
	
	
	
}
