package com.example.demo;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Publicacion;
import com.example.demo.model.Usuario;
import com.example.demo.repository.IUsuarioDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RedSocialApplication implements CommandLineRunner {

	@Autowired
	EntityManager em;
	@Autowired
	IUsuarioDAO dao;

	public static void main(String[] args) {
		SpringApplication.run(RedSocialApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
	}

}
