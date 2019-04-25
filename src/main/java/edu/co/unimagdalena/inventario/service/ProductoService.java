package edu.co.unimagdalena.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.co.unimagdalena.inventario.entity.Producto;
import edu.co.unimagdalena.inventario.repository.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository repository;
	
	public List<Producto> getAllProductos(){
		return (List<Producto>) repository.findAll();
	}
	
	public Producto getProducto(Long id) {
		return repository.getOne(id);
	}
	
	public Producto createProducto(Producto producto) {
		return repository.save(producto);
	}
	
	public Producto updateProducto(Producto producto) {
		return repository.save(producto);
	}
	
	public void deleteProducto(Long id) {
		repository.deleteById(id);
	}
	
}
