package edu.co.unimagdalena.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.unimagdalena.inventario.entity.Compra;
import edu.co.unimagdalena.inventario.repository.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository repository;
	
	public List<Compra> getAllCompra(){
		return (List<Compra>) repository.findAll();
	}
	
	public Compra getCompra(Long id) {
		return repository.getOne(id);
	}
	
	public Compra createCompra(Compra compra) {
		return repository.save(compra);
	}
	
	public Compra updateProducto(Compra compra) {
		return repository.save(compra);
	}
	
	public void deleteCompra(Long id) {
		repository.deleteById(id);
	}
}
