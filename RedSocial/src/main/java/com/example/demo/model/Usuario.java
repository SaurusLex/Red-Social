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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nick;
	String email;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "amigos", 
			joinColumns = 			@JoinColumn(name = "id_usuario_amigo"), 
			inverseJoinColumns = 	@JoinColumn(name = "id_usuario_amigoDE"))
	private List<Usuario> misAmigos = new ArrayList<Usuario>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "amigos", 
			joinColumns = 			@JoinColumn(name = "id_usuario_amigoDE"), 
			inverseJoinColumns = 	@JoinColumn(name = "id_usuario_amigo"))
	private List<Usuario> amigoDe = new ArrayList<Usuario>();
	
	
	@OneToOne(
			mappedBy = "usuario", 
			cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY)
	private Perfil perfil;

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


	public List<Usuario> getMisAmigos() {
		return misAmigos;
	}

	public void setMisAmigos(List<Usuario> misAmigos) {
		this.misAmigos = misAmigos;
	}

	public List<Usuario> getAmigoDe() {
		return amigoDe;
	}

	public void setAmigoDe(List<Usuario> amigosDe) {
		this.amigoDe = amigosDe;
	}

	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nick=" + nick + ", email=" + email + ", misAmigos=" + misAmigos + ", amigoDe="
				+ amigoDe + ", perfil=" + perfil + "]";
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amigoDe == null) ? 0 : amigoDe.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((misAmigos == null) ? 0 : misAmigos.hashCode());
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
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
		if (amigoDe == null) {
			if (other.amigoDe != null)
				return false;
		} else if (!amigoDe.equals(other.amigoDe))
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
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		return true;
	}

	public void addFriend(Usuario amigo) {
		try {
			misAmigos.add(amigo);
			amigo.amigoDe.add(this);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void removeFriend(Usuario amigo) {
		try {

			misAmigos.remove(amigo);
			amigoDe.remove(amigo);
			amigo.misAmigos.remove(this);
			amigo.amigoDe.remove(this);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isFriend(Usuario amigo) {
		if (misAmigos.contains(amigo)&&amigoDe.contains(amigo))
			return true;
		else
			return false;

	}
	
	

}
