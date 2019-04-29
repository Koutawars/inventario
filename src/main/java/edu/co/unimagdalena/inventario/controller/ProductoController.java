package edu.co.unimagdalena.inventario.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method=RequestMethod.POST, value="/buscar")
	public ResponseEntity<List<Producto>> searchByNameProducto(@RequestBody(required=false) String nombre){
		List<Producto> response = new ArrayList<Producto>();
		if(nombre != null) response = service.getProductoByName(nombre);
		return new ResponseEntity<List<Producto>>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable Long id){
		Producto response = service.getProducto(id);
		return new ResponseEntity<Producto>(response, HttpStatus.OK);
	}
		
	@RequestMapping(method=RequestMethod.POST, value = "/add")
	public ResponseEntity<Producto> addEstudiante(@RequestBody Producto producto) throws SQLIntegrityConstraintViolationException {
		Producto response;
		try {
			response = service.createProducto(producto);
		}catch(Exception e) {
			return ResponseEntity.status(409).build();
		}
		return new ResponseEntity<Producto>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/delete")
	public void deleteProducto(@RequestBody long id) {
		service.deleteProducto(id);
	}

	@RequestMapping(method=RequestMethod.PUT, value = "/update")
	public ResponseEntity<Producto> updateEstudiante(@RequestBody Producto producto) {
		Producto response = service.updateProducto(producto);
		if(response == null) {
			return ResponseEntity.status(512).build();
		}
		return new ResponseEntity<Producto>(response, HttpStatus.OK);
	}
	
}
