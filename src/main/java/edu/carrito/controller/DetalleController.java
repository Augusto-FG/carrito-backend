package edu.carrito.controller;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/detalle")
public class DetalleController {

    @Autowired
    CarritoService carritoService;
    
    @Autowired
    ProductoService productoService;

    @GetMapping("/carrito/{id}")
    public ResponseEntity<Set<Producto>> findById(@PathVariable int id) {
        Optional<Carrito> optCarrito = carritoService.findById(id);
        if (optCarrito.isPresent()) {
            return new ResponseEntity<>(optCarrito.get().getProductos(), HttpStatus.OK);
        } else {
            // Retorna un array vacío y mensaje
            return new ResponseEntity<>(Set.of(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<String> add(@RequestBody FormCarritoProducto form) {
        Optional<Carrito> optCarrito = carritoService.findById(form.getCarritoId());
        if (optCarrito.isPresent()) {
            Optional<Producto> optProd = productoService.findById(form.getProductoId());
            if (optProd.isPresent()) {
                Carrito carrito = optCarrito.get();
                carrito.getProductos().add(optProd.get());
                carrito = carritoService.save(carrito);
                return new ResponseEntity<>("Producto agregado con éxito al carrito: " + carrito.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error: El producto no está presente.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Error: El carrito no está presente.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cerrar")
    public ResponseEntity<String> buy(@RequestBody FormCarritoProducto form) {
        Optional<Carrito> optCarrito = carritoService.findById(form.getCarritoId());
        if (optCarrito.isPresent()) {
            Carrito compra = optCarrito.get();
            compra.setEstado(EstadoCarrito.CERRADO);
            carritoService.save(compra);
            return new ResponseEntity<>("Carrito cerrado con éxito: " + compra.getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Error: El carrito no está presente.", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> remove(@RequestBody FormCarritoProducto form) {
        Optional<Carrito> optCarrito = carritoService.findById(form.getCarritoId());
        if (optCarrito.isPresent()) {
            Carrito carrito = optCarrito.get();
            Optional<Producto> optProd = productoService.findById(form.getProductoId());
            if (optProd.isPresent()) {
                carrito.getProductos().remove(optProd.get());
                carrito = carritoService.save(carrito);
                return new ResponseEntity<>("Producto eliminado con éxito del carrito: " + carrito.getId(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error: El producto no está presente.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Error: El carrito no está presente.", HttpStatus.BAD_REQUEST);
    }
}
