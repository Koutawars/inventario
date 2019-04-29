package edu.co.unimagdalena.inventario.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	String fecha;
	
	
	@Column
	String cliente;
	
	@Column
	private long total;

	@JsonIgnoreProperties("compra")
	@OneToMany(mappedBy = "compra")
	private List<Detalle> detalles;
	
	public Compra() {
		super();
	}

	@Override
	public String toString() {
		return "Compra [fecha=" + fecha + ", cliente=" + cliente + ", total=" + total + ", detalles=" + detalles + "]";
	}

	public Compra(long id, String fecha, String cliente, long total, List<Detalle> detalles) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cliente = cliente;
		this.total = total;
		this.detalles = detalles;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	
	
	
}
