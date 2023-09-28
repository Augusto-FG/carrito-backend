package edu.carrito.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import edu.carrito.util.EstadoCarrito;
import edu.carrito.util.TipoCarrito;

@Entity
public class Carrito {
	public Carrito() {
	}

	public Carrito(TipoCarrito tipo) {
		this.tipo = tipo;
		this.estado = EstadoCarrito.ABIERTO;
		this.productos = new HashSet<Producto>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private TipoCarrito tipo;
	@Column
	private EstadoCarrito estado;
	@ManyToMany
	Set<Producto> productos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoCarrito getTipo() {
		return tipo;
	}

	public void setTipo(TipoCarrito tipo) {
		this.tipo = tipo;
	}

	public EstadoCarrito getEstado() {
		return estado;
	}

	public void setEstado(EstadoCarrito estado) {
		this.estado = estado;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

}
