package edu.co.unimagdalena.inventario.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.unimagdalena.inventario.entity.Compra;
import edu.co.unimagdalena.inventario.service.CompraService;


@RequestMapping("/comprar")
@RestController
public class CompraController {
	@Autowired
	CompraService service;
	
	@RequestMapping
	public ResponseEntity<List<Compra>> getCompra() {
		List<Compra> response =  service.getAllCompra();
		return new ResponseEntity<List<Compra>>(response, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, value = "/add")
	public ResponseEntity<Compra> addCompra(@RequestBody Compra compra) throws SQLIntegrityConstraintViolationException {
		Compra response;
		try {
			response = service.createCompra(compra);
		}catch(Exception e) {
			return ResponseEntity.status(409).build();
		}
		return new ResponseEntity<Compra>(response, HttpStatus.CREATED);
	}
}
