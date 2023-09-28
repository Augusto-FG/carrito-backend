package edu.carrito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.carrito.model.Carrito;
import edu.carrito.model.Producto;
import edu.carrito.service.CarritoService;
import edu.carrito.service.ProductoService;
import edu.carrito.util.TipoCarrito;

@SpringBootApplication
public class CarritoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);
	}

	@Autowired
	CarritoService carritoService;
	@Autowired
	ProductoService productoService;

	@Override
	public void run(String... args) throws Exception {

		Producto prod1 = new Producto();
		prod1.setDescripcion("mouse");
		prod1.setPrecio(100f);
		productoService.save(prod1);

		Producto prod2 = new Producto();
		prod2.setDescripcion("teclado");
		prod2.setPrecio(200f);
		productoService.save(prod2);

		Carrito carrito = new Carrito(TipoCarrito.COMUN);
		carrito.getProductos().add(prod1);
		carritoService.save(carrito);
	}

}
