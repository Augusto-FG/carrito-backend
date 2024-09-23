package edu.carrito.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import edu.carrito.model.Producto;
import edu.carrito.service.ProductoService;
import org.mockito.InjectMocks;

public class ProductoControllerTest {
    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }
/**
 * Test para ProductoController:
 
 *  - testSave_Success: Verifica que al intentar crear un producto con datos válidos,
     el controlador responda con un estado HTTP 201 (Created) y un mensaje de éxito,
     junto con los detalles del producto que creamos.
 
 * - testSave_BadRequest: Simula una excepción durante la creación de un producto y
     asegura que el controlador responda con un estado HTTP 400 (Bad Request) y un
     mensaje de error.
 */
    @Test
    public void testSave_Success() throws Exception {
        // Arrange
        Producto producto = new Producto();
        producto.setId(1);
        producto.setDescripcion("Producto 1");
        producto.setPrecio(100f);

        when(productoService.save(any(Producto.class))).thenReturn(producto);

        // Act & Assert
        mockMvc.perform(post("/productos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"descripcion\":\"Producto 1\",\"precio\":100.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Producto creado correctamente"))
                .andExpect(jsonPath("$.producto.id").value(1));
    }

    @Test
    public void testSave_BadRequest() throws Exception {
        // Arrange
        when(productoService.save(any(Producto.class))).thenThrow(new RuntimeException("Error al guardar el producto"));

        // Act & Assert
        mockMvc.perform(post("/productos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"descripcion\":\"Producto 1\",\"precio\":100.0}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Error al guardar el producto: Error al guardar el producto"));
    }
}

