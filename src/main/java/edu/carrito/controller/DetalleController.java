package edu.carrito.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.carrito.model.Carrito;
import edu.carrito.model.Producto;
import edu.carrito.service.CarritoService;
import edu.carrito.service.ProductoService;
import edu.carrito.util.EstadoCarrito;
import edu.carrito.util.FormCarritoProducto;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DetalleController {

	@Autowired
	CarritoService carritoService;
	@Autowired
	ProductoService productoService;

	@GetMapping("/detalle/{id}")
	public Set<Producto> findById(@PathVariable int id) {
		Optional<Carrito> optCarrito = carritoService.findById(id);
		if (optCarrito.isPresent()) {
			return optCarrito.get().getProductos();
		} else
			return null;
	}

	@PostMapping("/detalle")
	public ResponseEntity<Carrito> add(@RequestBody FormCarritoProducto form) {
		Optional<Carrito> optCarrito = carritoService.findById(form.getCarritoId());
		if (optCarrito.isPresent()) {
			Optional<Producto> optProd = productoService.findById(form.getProductoId());
			if (optProd.isPresent()) {
				Carrito carrito = optCarrito.get();
				carrito.getProductos().add(optProd.get());
				carrito = carritoService.save(carrito);
				return new ResponseEntity<Carrito>(carrito, HttpStatus.OK);
			}
		}

		return new ResponseEntity<Carrito>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/comprar")
	public ResponseEntity<Carrito> buy(@RequestBody FormCarritoProducto form) {
		Optional<Carrito> optCarrito = carritoService.findById(form.getCarritoId());
		if (optCarrito.isPresent()) {
			Carrito compra = optCarrito.get();
			compra.setEstado(EstadoCarrito.CERRADO);
			carritoService.save(compra);
			return new ResponseEntity<Carrito>(compra, HttpStatus.OK);
		}

		return new ResponseEntity<Carrito>(HttpStatus.BAD_REQUEST);
	}

}
