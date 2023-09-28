package edu.carrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.carrito.model.Producto;
import edu.carrito.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	public Iterable<Producto> findAll() {
		return productoRepository.findAll();
	}

	public Producto save(Producto c) {
		return productoRepository.save(c);
	}

	public void delete(int id) {
		productoRepository.deleteById(id);
	}

	public Optional<Producto> findById(int id) {
		return productoRepository.findById(id);
	}
}
