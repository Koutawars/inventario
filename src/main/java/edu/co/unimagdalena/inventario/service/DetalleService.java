package edu.co.unimagdalena.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.unimagdalena.inventario.entity.Detalle;
import edu.co.unimagdalena.inventario.repository.DetalleRepository;

@Service
public class DetalleService {
	@Autowired
	DetalleRepository repository;
	
	public List<Detalle> getAllDetalles(){
		return (List<Detalle>) repository.findAll();
	}
	
	public Detalle getDetalle(Long id) {
		return repository.getOne(id);
	}
	
	public Detalle createDetalle(Detalle detalle) {
		return repository.save(detalle);
	}
	
	public Detalle updateDetalle(Detalle detalle) {
		return repository.save(detalle);
	}
	
	public void deleteDetalle(Long id) {
		repository.deleteById(id);
	}
}
