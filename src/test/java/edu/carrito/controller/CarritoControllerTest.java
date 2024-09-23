package edu.carrito.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import edu.carrito.model.Carrito;
import edu.carrito.service.CarritoService;
import java.util.Optional;

public class CarritoControllerTest {
    
/**
//Hacemos unit test con Mockito:    
//testFindById_Success: verifica que al solicitar un carrito existente (ID 1), 
// el controlador responde con un estado HTTP 200 (OK) y devuelva el carrito correspondiente en formato JSON.
 
//testFindById_NotFound: verifica que al solicitar un carrito no existente (ID 2),
//el controlador responda con un estado HTTP 404 (Not Found).    
 **/   
    @InjectMocks
    private CarritoController carritoController;

    @Mock
    private CarritoService carritoService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carritoController).build();
    }

    @Test
    public void testFindById_Success() throws Exception {
        // Arrange
        Carrito carrito = new Carrito(); // Configuro el carrito del model
        carrito.setId(1);
        when(carritoService.findById(1)).thenReturn(Optional.of(carrito));

        // Act & Assert
        mockMvc.perform(get("/carritos/traer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1)); // Asegúrate de que la estructura coincida
    }

    @Test
    public void testFindById_NotFound() throws Exception {
        // Arrange
        when(carritoService.findById(2)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/carritos/traer/2"))
                .andExpect(status().isNotFound());
    }
}
