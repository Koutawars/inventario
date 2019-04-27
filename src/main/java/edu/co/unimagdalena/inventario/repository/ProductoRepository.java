package edu.co.unimagdalena.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unimagdalena.inventario.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByNombreContaining(String nombre);
}
