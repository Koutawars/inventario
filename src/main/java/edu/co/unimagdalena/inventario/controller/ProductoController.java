package edu.co.unimagdalena.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.co.unimagdalena.inventario.entity.Producto;
import edu.co.unimagdalena.inventario.service.ProductoService;

@RequestMapping("/producto")
@RestController
public class ProductoController {
	@Autowired
	ProductoService service;
	
	@RequestMapping
	public ResponseEntity<List<Producto>> getProductos() {
		List<Producto> response =  service.getAllProductos();
		return new ResponseEntity<List<Producto>>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable Long id){
		Producto response = service.getProducto(id);
		return new ResponseEntity<Producto>(response, HttpStatus.OK);
	}
	
}
