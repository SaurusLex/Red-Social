package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	
	
	
	@Id
	@Column(name = "id", unique = true)
	int id;
	String nick;
	String email;
	@Column(nullable = true)
	int edad;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable
	(
			name = "amigos",
			joinColumns = @JoinColumn(name = "id_usuario_amigo", insertable = true),
			inverseJoinColumns = @JoinColumn(name = "id_usuario_amigoDE", insertable = true)
	)
	private List<Usuario> misAmigos; 
	
	@JsonIgnore
	@ManyToMany(mappedBy = "misAmigos", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Usuario> amigosDe;

	
	public Usuario() { 
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public List<Usuario> getMisAmigos() {
		return misAmigos;
	}


	public void setMisAmigos(List<Usuario> misAmigos) {
		this.misAmigos = misAmigos;
	}


	public List<Usuario> getAmigosDe() {
		return amigosDe;
	}


	public void setAmigosDe(List<Usuario> amigosDe) {
		this.amigosDe = amigosDe;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", email=" + email + ", edad=" + edad + ", misAmigos="
				+ misAmigos + ", amigosDe=" + amigosDe + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amigosDe == null) ? 0 : amigosDe.hashCode());
		result = prime * result + edad;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((misAmigos == null) ? 0 : misAmigos.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (amigosDe == null) {
			if (other.amigosDe != null)
				return false;
		} else if (!amigosDe.equals(other.amigosDe))
			return false;
		if (edad != other.edad)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (misAmigos == null) {
			if (other.misAmigos != null)
				return false;
		} else if (!misAmigos.equals(other.misAmigos))
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		return true;
	}
	
	public void addFriend(Usuario amigo){
		try {
			misAmigos.add(amigo);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
