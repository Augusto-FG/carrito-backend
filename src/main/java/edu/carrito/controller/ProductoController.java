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
import edu.carrito.model.Producto;
import edu.carrito.service.ProductoService;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/traer")
    public ResponseEntity<Iterable<Producto>> getAll() {
        return new ResponseEntity<>(productoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        Optional<Producto> optProducto = productoService.findById(id);
        if (optProducto.isPresent()) {
            return new ResponseEntity<>(optProducto.get(), HttpStatus.OK);
         } else {
            return new ResponseEntity<>(Map.of("message", "Producto no encontrado."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
     public ResponseEntity<?> save(@RequestBody Producto c) {
        try {
        Producto newProducto = productoService.save(c);
        return new ResponseEntity<>(
            Map.of("message", "Producto creado correctamente", "producto", newProducto),
            HttpStatus.CREATED);
         } catch (Exception e) {
        return new ResponseEntity<>(
            Map.of("message", "Error al guardar el producto: " + e.getMessage()),
            HttpStatus.BAD_REQUEST);
     }
     }


    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody Producto c) {
        Optional<Producto> optProducto = productoService.findById(c.getId());
        if (optProducto.isPresent()) {
            Producto updatedProducto = productoService.save(c);
            return new ResponseEntity<>( Map.of("message", "Producto modificado correctamente", "producto", updatedProducto),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "Producto no encontrado para editar."), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Producto> optProducto = productoService.findById(id);
        if (optProducto.isPresent()) {
            productoService.delete(id);
            return new ResponseEntity<>(Map.of("message", "Producto eliminado con éxito."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "Producto no encontrado para eliminar."), HttpStatus.NOT_FOUND);
        }
    }
}
