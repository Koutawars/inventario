package edu.co.unimagdalena.inventario.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="detalle")
public class Detalle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String cliente;
	
	@Column
	private int cantidad;
	
	@JsonIgnoreProperties("detalles")
	@ManyToOne
	@JoinColumn(name = "producto")
	private Producto producto;

	@JsonIgnoreProperties("detalles")
	@ManyToOne
	@JoinColumn(name = "compra")
	private Compra compra;
	

	public Detalle() {
		super();
	}
	
	public Detalle(long id, Producto producto, Compra compra, int cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.compra = compra;
		this.cantidad = cantidad;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
