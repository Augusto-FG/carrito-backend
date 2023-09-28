package edu.carrito.repository;

import org.springframework.data.repository.CrudRepository;

import edu.carrito.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {

}
