package edu.co.unimagdalena.inventario.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.co.unimagdalena.inventario.entity.Detalle;
import edu.co.unimagdalena.inventario.service.DetalleService;

@RequestMapping("/detalle")
@RestController
public class DetalleController {
	@Autowired
	DetalleService service;

	@RequestMapping
	public ResponseEntity<List<Detalle>> getDetalles() {
		List<Detalle> response =  service.getAllDetalles();
		return new ResponseEntity<List<Detalle>>(response, HttpStatus.OK);
	}
	
	@RequestMapping("/{id}")
	public ResponseEntity<Detalle> getDetalle(@PathVariable Long id){
		Detalle response = service.getDetalle(id);
		return new ResponseEntity<Detalle>(response, HttpStatus.OK);
	}
		
	@RequestMapping(method=RequestMethod.POST, value = "/add")
	public ResponseEntity<Detalle> addDetalle(@RequestBody Detalle detalle) throws SQLIntegrityConstraintViolationException {
		Detalle response;
		try {
			response = service.createDetalle(detalle);
		}catch(Exception e) {
			return ResponseEntity.status(409).build();
		}
		return new ResponseEntity<Detalle>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value = "/delete")
	public void deleteDetalle(@RequestBody long id) {
		service.deleteDetalle(id);
	}

	@RequestMapping(method=RequestMethod.PUT, value = "/update")
	public ResponseEntity<Detalle> updateEstudiante(@RequestBody Detalle detalle) {
		Detalle response = service.updateDetalle(detalle);
		if(response == null) {
			return ResponseEntity.status(512).build();
		}
		return new ResponseEntity<Detalle>(response, HttpStatus.OK);
	}
}
