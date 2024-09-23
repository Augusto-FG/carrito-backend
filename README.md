# 🚀 Backend - Despliegue Local

Esta aplicación es un **sistema de gestión de carritos de compra** que permite a los usuarios crear y manejar carritos, así como agregar y eliminar productos. Además, permite gestionar la información de los productos disponibles para la compra. La aplicación proporciona una interfaz **RESTful** que facilita las operaciones relacionadas con carritos y productos, permitiendo una integración sencilla con otros sistemas y aplicaciones front-end.

## 🛠️ Ejecución del Backend

### Requisitos
Se aconseja utilizar una versión estable de **Apache NetBeans** o cualquier otro IDE estable, también se puede utilizar **Eclipse**. La aplicación corre en [http://localhost:8080](http://localhost:8080).

### En Apache NetBeans:
1. **Abrir el Proyecto:**
   - Abre NetBeans.
   - Ve a `File` > `Open Project`.
   - Navega hasta la carpeta del proyecto Spring Boot que deseas abrir y selecciona la carpeta del proyecto (debería contener el archivo `pom.xml`).
   - Haz clic en `Open Project`.

2. **Ejecutar la Aplicación:**
   - Antes de ejecutar el proyecto, instala las dependencias necesarias con un clic derecho sobre el proyecto y apretando `Clean and Build`.
   - Busca la clase principal que contiene el método `main`. Haz clic derecho en la clase principal y selecciona `Run File` o usa el atajo `Shift + F6`.
   - Alternativamente, puedes ejecutar el proyecto completo haciendo clic derecho en el proyecto en el panel de proyectos y seleccionando `Run`.

### En Eclipse:
1. **Importar el Proyecto:**
   - Abre Eclipse.
   - Ve a `File` > `Import`.
   - Selecciona `Existing Maven Projects` y haz clic en `Next`.
   - Navega hasta la carpeta del proyecto Spring Boot que deseas abrir y selecciona la carpeta (debería contener el archivo `pom.xml`).
   - Haz clic en `Finish`.

2. **Ejecutar la Aplicación:**
   - Busca la clase principal que contiene el método `main`.
   - Haz clic derecho en la clase y selecciona `Run As` > `Java Application`.
   - También puedes ejecutar el proyecto completo haciendo clic derecho en el proyecto en el panel de `Package Explorer` y seleccionando `Run As` > `Maven install` o `Maven build`.

## 🗄️ Base de Datos
El proyecto posee una base de datos **H2 en memoria** (`jdbc:h2:mem:testdb`). Esto significa que no está almacenada en disco: la base de datos existe solo en la memoria mientras la aplicación se está ejecutando. Cuando la aplicación Spring Boot se detiene, toda la información en la base de datos se pierde.

### Acceso a la Base de Datos
Para acceder a la base de datos de la aplicación, dirígete a [http://localhost:8080/h2-console/login.jsp](http://localhost:8080/h2-console/login.jsp).

### Datos de Conexión
- **Setting Name:** Generic H2 (Embedded)
- **Driver Class:** org.h2.Driver
- **JDBC URL:** jdbc:h2:mem:testdb
- **User Name:** sa
- **Password:** (vacía, sin contraseña)

En la base de datos verás las siguientes tablas: **CARRITO**, **CARRITO_PRODUCTOS** y **PRODUCT**.

## 📚 Documentación de Controladores
Todos los endpoints se encuentran documentados en la API de Postman y se pueden ver en detalle en el siguiente enlace: [Documentación Postman](https://documenter.getpostman.com/view/33675389/2sAXqv3f23).

También se encuentran en **Swagger**, una vez ejecutado el backend, en el siguiente enlace: [Swagger UI](http://localhost:8080/swagger-ui/index.html#/).

### Descripción de Todos los Controladores

#### 1. CarritoController:
- **GET /carritos/traer**
  - **Descripción:** Obtiene todos los carritos de compra existentes.
  - **Respuesta:** Devuelve una lista de carritos.

- **GET /carritos/traer/{id}**
  - **Descripción:** Busca un carrito específico por su ID.
  - **Respuesta:** Devuelve el carrito encontrado o un mensaje de error si no existe.

- **POST /carritos/guardar**
  - **Descripción:** Crea un nuevo carrito con los detalles proporcionados.
  - **Respuesta:** Devuelve un mensaje de éxito y el ID del carrito creado.

- **PUT /carritos/modificar**
  - **Descripción:** Modifica los detalles de un carrito existente.
  - **Respuesta:** Devuelve un mensaje de éxito y el ID del carrito modificado.

- **DELETE /carritos/eliminar/{id}**
  - **Descripción:** Elimina un carrito específico por su ID.
  - **Respuesta:** Devuelve un mensaje de éxito o un mensaje de error si el carrito no existe.

#### 2. DetalleController:
- **GET /detalles/carrito/{id}**
  - **Descripción:** Obtiene los productos asociados a un carrito específico.
  - **Respuesta:** Devuelve la lista de productos o un mensaje si el carrito no existe.

- **POST /detalles/guardar**
  - **Descripción:** Agrega un producto a un carrito existente.
  - **Respuesta:** Devuelve un mensaje de éxito o un error si el carrito o el producto no existen.

- **POST /detalles/cerrar**
  - **Descripción:** Cierra un carrito, cambiando su estado a cerrado.
  - **Respuesta:** Devuelve un mensaje de éxito o un error si el carrito no existe.

- **DELETE /detalles/eliminar**
  - **Descripción:** Elimina un producto de un carrito específico.
  - **Respuesta:** Devuelve un mensaje de éxito o un error si el carrito o el producto no existen.

#### 3. ProductoController:
- **GET /productos/traer**
  - **Descripción:** Obtiene todos los productos disponibles.
  - **Respuesta:** Devuelve una lista de productos.

- **GET /productos/traer/{id}**
  - **Descripción:** Busca un producto específico por su ID.
  - **Respuesta:** Devuelve el producto encontrado o un mensaje de error si no existe.

- **POST /productos/crear**
  - **Descripción:** Crea un nuevo producto con los detalles proporcionados.
  - **Respuesta:** Devuelve un mensaje de éxito y los detalles del producto creado.

- **PUT /productos/editar**
  - **Descripción:** Modifica los detalles de un producto existente.
  - **Respuesta:** Devuelve un mensaje de éxito y los detalles del producto modificado.

- **DELETE /productos/eliminar/{id}**
  - **Descripción:** Elimina un producto específico por su ID.
  - **Respuesta:** Devuelve un mensaje de éxito o un mensaje de error si el producto no existe.

## 🧪 Test con Mockito
Los test se encuentran en la carpeta **Test**, donde se testean dos controladores:
- **CarritoController:** se prueba traer un carrito por ID y simular un bad request.
- **ProductoController:** donde se intenta crear un producto nuevo y simular un bad request.
