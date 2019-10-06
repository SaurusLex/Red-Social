package com.example.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.control.Control;
import com.example.demo.repository.IUsuarioDAO;

@SpringBootTest
public class TestControlador {

	@Autowired
	IUsuarioDAO dao;
	
	@Test
	public void amigos() {
		
	}
	
}
