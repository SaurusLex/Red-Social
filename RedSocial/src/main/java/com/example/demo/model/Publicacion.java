package com.example.demo.model;

import java.time.LocalDateTime;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publicaciones")
public class Publicacion {
	
	
	@Id
	@Column(name = "id", unique = true)
	int Id;
	
	String titulo;
	
	String cuerpo;
	
	@Column(columnDefinition = "DATATIME")
	LocalDateTime fecha;
	
	@ManyToOne
	(
		cascade = CascadeType.REFRESH, 
		fetch=FetchType.LAZY	
	)
	@JoinColumn(name = "id_usuario")
	Usuario usuario;


	
	
	

   
    
    
	
	

}
