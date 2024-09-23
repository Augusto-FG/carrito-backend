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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
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
        @Enumerated(EnumType.STRING) // Esto es importante
	@Column
	private TipoCarrito tipo;
        @Enumerated(EnumType.STRING)
	@Column
	private EstadoCarrito estado;
	@ManyToMany
	Set<Producto> productos;

}
