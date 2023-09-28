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

import edu.carrito.model.Producto;
import edu.carrito.service.ProductoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductoController {

	@Autowired
	ProductoService productoService;

	@GetMapping("/productos")
	public Iterable<Producto> getAll() {
		return productoService.findAll();
	}

	@GetMapping("/productos/{id}")
	public Producto findById(@PathVariable int id) {
		Optional<Producto> optProducto = productoService.findById(id);
		if (optProducto.isPresent()) {
			return optProducto.get();
		} else
			return null;
	}

	@PostMapping("/productos")
	public Producto save(@RequestBody Producto c) {
		Producto newProducto = productoService.save(c);
		return newProducto;
	}

	@PutMapping("/productos")
	public Producto update(@RequestBody Producto c) {
		return productoService.save(c);
	}

	@DeleteMapping("/productos/{id}")
	public void delete(@PathVariable int id) {
		productoService.delete(id);
	}

}
