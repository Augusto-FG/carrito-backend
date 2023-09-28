package edu.carrito.repository;

import org.springframework.data.repository.CrudRepository;

import edu.carrito.model.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito, Integer> {

}
