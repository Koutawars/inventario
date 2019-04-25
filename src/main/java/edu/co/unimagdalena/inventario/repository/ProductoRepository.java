package edu.co.unimagdalena.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.co.unimagdalena.inventario.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
