package edu.carrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.carrito.model.Carrito;
import edu.carrito.repository.CarritoRepository;

@Service
public class CarritoService {

	@Autowired
	CarritoRepository carritoRepository;

	public Iterable<Carrito> findAll() {
		return carritoRepository.findAll();
	}

	public Carrito save(Carrito c) {
		return carritoRepository.save(c);
	}

	public void delete(int id) {
		carritoRepository.deleteById(id);
	}

	public Optional<Carrito> findById(int id) {
		return carritoRepository.findById(id);
	}
}
