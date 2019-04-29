package edu.co.unimagdalena.inventario.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	@Temporal(TemporalType.DATE)
	Date fecha;
	
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

	public Compra(long id, Date fecha, String cliente, long total, List<Detalle> detalles) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.total = total;
		this.cliente = cliente;
		this.detalles = detalles;
	}

	
	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}
	
	
}
