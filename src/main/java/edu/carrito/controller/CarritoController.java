package edu.carrito.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.carrito.model.Carrito;
import edu.carrito.service.CarritoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CarritoController {

	@Autowired
	CarritoService carritoService;

	@GetMapping("/carritos")
	public Iterable<Carrito> getAll() {
		return carritoService.findAll();
	}

	@GetMapping("/carritos/{id}")
	public Carrito findById(@PathVariable int id) {
		Optional<Carrito> optCarrito = carritoService.findById(id);
		if (optCarrito.isPresent()) {
			return optCarrito.get();
		} else
			return null;
	}

	@PostMapping("/carritos")
	public Carrito save(@RequestBody Carrito c) {
		Carrito newCarrito = carritoService.save(c);
		return newCarrito;
	}

	@PutMapping("/carritos")
	public Carrito update(@RequestBody Carrito c) {
		return carritoService.save(c);
	}

	@DeleteMapping("/carritos/{id}")
	public void delete(@PathVariable int id) {
		carritoService.delete(id);
	}

}
