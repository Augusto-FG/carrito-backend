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
		prod1.setDescripcion("Mouse");
		prod1.setPrecio(100f);
		productoService.save(prod1);

		Producto prod2 = new Producto();
		prod2.setDescripcion("Teclado");
		prod2.setPrecio(200f);
		productoService.save(prod2);
                
                Producto prod3 = new Producto();
                prod3.setDescripcion("Monitor");
                prod3.setPrecio(300f);
                productoService.save(prod3);

                Producto prod4 = new Producto();
                prod4.setDescripcion("Parlante");
                prod4.setPrecio(150f);
                productoService.save(prod4);

		Carrito carrito = new Carrito(TipoCarrito.COMUN);
		carrito.getProductos().add(prod1);
		carritoService.save(carrito);
                
                Carrito nuevoCarrito = new Carrito(TipoCarrito.VIP);
                nuevoCarrito.getProductos().add(prod1);
                nuevoCarrito.getProductos().add(prod2);
                nuevoCarrito.getProductos().add(prod3);
                nuevoCarrito.getProductos().add(prod4); 
                carritoService.save(nuevoCarrito); 
	}

}
