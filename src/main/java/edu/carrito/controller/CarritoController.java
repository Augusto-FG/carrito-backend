package edu.carrito.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.carrito.model.Carrito;
import edu.carrito.service.CarritoService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/traer")
    public ResponseEntity<Iterable<Carrito>> getAll() {
        return new ResponseEntity<>(carritoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Carrito> findById(@PathVariable int id) {
        Optional<Carrito> optCarrito = carritoService.findById(id);
        return optCarrito.map(carrito -> new ResponseEntity<>(carrito, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> save(@RequestBody Carrito c) {
        Carrito newCarrito = carritoService.save(c);
        return new ResponseEntity<>("Carrito creado con éxito: " + newCarrito.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> update(@RequestBody Carrito c) {
        Carrito updatedCarrito = carritoService.save(c);
        return new ResponseEntity<>("Carrito modificado con éxito: " + updatedCarrito.getId(), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if (carritoService.findById(id).isPresent()) {
            carritoService.delete(id);
            return new ResponseEntity<>("Carrito eliminado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Carrito no encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
